package com.booknet.api.authentication.controller;

import com.booknet.api.authentication.model.AppRole;
import com.booknet.api.authentication.model.AppUser;
import com.booknet.api.authentication.model.EAppRole;
import com.booknet.api.authentication.payload.request.LoginRequest;
import com.booknet.api.authentication.payload.request.SignupRequest;
import com.booknet.api.authentication.payload.response.JwtResponse;
import com.booknet.api.authentication.payload.response.MessageResponse;
import com.booknet.api.authentication.repository.IAppRoleRepository;
import com.booknet.api.authentication.repository.IAppUserRepository;
import com.booknet.api.authentication.security.jwt.JwtUtils;
import com.booknet.api.authentication.security.services.AppUserDetails;
import com.booknet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IAppUserRepository userRepository;

    @Autowired
    IAppRoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        Utils.log.print(this,
                "User @username logged with authorities @authorities"
                        .replace("@username", userDetails.getUsername())
                        .replace("@authorities", Utils.json.stringify(userDetails.getAuthorities()))
        );

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        AppUser user = new AppUser(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<AppRole> roles = new HashSet<>();

        if (strRoles == null) {
            AppRole userRole = roleRepository.findByName(EAppRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: AppRole is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        AppRole adminRole = roleRepository.findByName(EAppRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: ADMIN ROLE is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        AppRole modRole = roleRepository.findByName(EAppRole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: MODERATOR ROLE is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        AppRole userRole = roleRepository.findByName(EAppRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: USER ROLE is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("AppUser registered successfully!"));
    }
}
