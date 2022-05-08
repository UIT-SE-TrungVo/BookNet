package com.booknet.api.account.authentication.service;

import com.booknet.api.account.authentication.model.AppRole;
import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.account.authentication.model.EAppRole;
import com.booknet.api.account.authentication.payload.request.LoginRequest;
import com.booknet.api.account.authentication.payload.request.SignupRequest;
import com.booknet.api.account.authentication.payload.response.JwtResponse;
import com.booknet.api.account.authentication.repository.AppRoleRepository;
import com.booknet.api.account.authentication.repository.AppUserRepository;
import com.booknet.api.account.authentication.security.jwt.JwtUtils;
import com.booknet.api.account.authentication.security.services.AppUserDetails;
import com.booknet.constants.ErrCode;
import com.booknet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    AppRoleRepository roleRepository;

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

    public long handleSignup(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ErrCode.REGISTER_USERNAME_TAKEN;
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ErrCode.REGISTER_EMAIL_TAKEN;
        }

        // Create new user's account
        AppUser user = new AppUser(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
        );

        Set<String> strRoles = signUpRequest.getRoles();
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

        user.setRoles(roles);
        userRepository.save(user);
        return ErrCode.NONE;
    }
}