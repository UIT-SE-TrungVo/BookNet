package com.booknet.api.account.authentication.service;

import com.booknet.api.account.authentication.config.AuthConfig;
import com.booknet.api.account.authentication.model.AppRole;
import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.account.authentication.model.EAppRole;
import com.booknet.api.account.authentication.model.VerifyingUser;
import com.booknet.api.account.authentication.payload.request.LoginRequest;
import com.booknet.api.account.authentication.payload.request.SignupRequest;
import com.booknet.api.account.authentication.payload.request.SignupVerifyRequest;
import com.booknet.api.account.authentication.payload.response.JwtResponse;
import com.booknet.api.account.authentication.repository.AppRoleRepository;
import com.booknet.api.account.authentication.repository.AppUserRepository;
import com.booknet.api.account.authentication.repository.VerifyingUserRepository;
import com.booknet.api.account.authentication.security.jwt.JwtUtils;
import com.booknet.api.account.authentication.security.services.AppUserDetails;
import com.booknet.constants.ErrCode;
import com.booknet.system.mail.MailService;
import com.booknet.system.mail.model.TextEmail;
import com.booknet.system.token_generator.TokenGenerator;
import com.booknet.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    VerifyingUserRepository verifyingUserRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public JwtResponse handleLogin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Utils.log.print(this,
                "User @username logged with authorities @authorities"
                        .replace("@username", userDetails.getUsername())
                        .replace("@authorities", Utils.json.stringify(userDetails.getAuthorities()))
        );
        Utils.log.print(this,
                "Token info @authentication @tokenInfo"
                        .replace("@authentication", Utils.json.stringify(authentication))
                        .replace("@tokenInfo", Utils.json.stringify(jwt))
        );

        return new JwtResponse(
                jwt
                , userDetails.getId()
                , userDetails.getUsername()
                , userDetails.getEmail()
                , roles
        );
    }

    public long createNewUser(SignupRequest signUpRequest) {
        var username = signUpRequest.getUsername();
        var email = signUpRequest.getEmail();

        if (this._isUsernameTaken(username)) {
            logger.error("User cannot be created {}: taken username", username);
            return ErrCode.REGISTER_USERNAME_TAKEN;
        }

        if (this._isUserEmailTaken(email)) {
            logger.error("User cannot be created {}: taken email", email);
            return ErrCode.REGISTER_EMAIL_TAKEN;
        }

        // Create new user's account
        VerifyingUser verifyingUser = new VerifyingUser(
                signUpRequest.getUsername()
                , signUpRequest.getEmail()
                , encoder.encode(signUpRequest.getPassword())
        );

        Set<String> strRoles = signUpRequest.getRoles();
        var roles = this._getAppUserRoles(strRoles);
        verifyingUser.setRoles(roles);
        verifyingUserRepository.save(verifyingUser);

        //generate verification code
        var tokenLength = AuthConfig.VERIFY_TOKEN_LENGTH;
        var tokenCharset = AuthConfig.VERIFY_TOKEN_LENGTH_CHAR_SET;
        String token = TokenGenerator.getRandomizedString(tokenLength, tokenCharset);
        verifyingUser.setToken(token);

        //send code to user email
        var subject = AuthConfig.MAIL_SUBJECT;
        var content = AuthConfig.MAIL_CONTENT.replace("@code", token);
        var validationEmail = new TextEmail();
        validationEmail.addRecipient(email);
        validationEmail.setSubject(subject);
        validationEmail.setContent(content);
        MailService.sendTextMail(validationEmail);

        logger.info("User has been created successfully {} - pending for verification", verifyingUser.get_id());
        return ErrCode.NONE;
    }

    public long verifyAccount(SignupVerifyRequest verifyRequest) {
        var email = verifyRequest.getEmail();
        if (!verifyingUserRepository.existsByEmail(email)) {
            logger.error("No verifying user has email {}", email);
            return ErrCode.VERIFY_INVALID_EMAIL;
        }
        var verifyingToken = verifyRequest.getToken();

        VerifyingUser verifyingUser = verifyingUserRepository.findByEmail(email);
        var inDbToken = verifyingUser.getToken();
        var expiryDate = verifyingUser.getExpiryDate();

        var tokenMatched = Objects.equals(inDbToken, verifyingToken);
        var tokenExpired = expiryDate.before(new Date());

        if (!tokenMatched) {
            logger.error("Mismatched token {} for user with email {}", verifyingToken, email);
            return ErrCode.VERIFY_TOKEN_MISMATCH;
        }

        if (tokenExpired){
            logger.error("Expired verification for user with email {}", email);
            return ErrCode.VERIFY_TOKEN_EXPIRED;
        }

        AppUser verifiedUser = new AppUser(verifyingUser);
        userRepository.save(verifiedUser);
        verifyingUserRepository.delete(verifyingUser);

        logger.info("User has been verified successfully {}", verifiedUser.get_id());
        return ErrCode.NONE;
    }

    private boolean _isUsernameTaken(String username) {
       return userRepository.existsByUsername(username)
                || verifyingUserRepository.existsByUsername(username);
    }

    private boolean _isUserEmailTaken(@Email String email) {
        return userRepository.existsByEmail(email)
                || verifyingUserRepository.existsByEmail(email);
    }

    @NotNull
    private Set<AppRole> _getAppUserRoles(Set<String> strRoles) {
        Set<AppRole> roles = new HashSet<>();

        if (strRoles == null) {
            AppRole userRole = roleRepository.findByName(EAppRole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: USER ROLE is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        AppRole adminRole = roleRepository.findByName(EAppRole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: ADMIN ROLE is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        AppRole modRole = roleRepository.findByName(EAppRole.MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: MODERATOR ROLE is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        AppRole userRole = roleRepository.findByName(EAppRole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: USER ROLE is not found."));
                        roles.add(userRole);
                }
            });
        }
        return roles;
    }
}