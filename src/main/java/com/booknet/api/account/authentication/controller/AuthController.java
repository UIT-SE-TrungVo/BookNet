package com.booknet.api.account.authentication.controller;

import com.booknet.api.account.authentication.payload.request.LoginRequest;
import com.booknet.api.account.authentication.payload.request.SignupRequest;
import com.booknet.api.account.authentication.service.AuthenticationService;
import com.booknet.base.payload.BaseResponse;
import com.booknet.constants.ErrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        var jwtResponse = authenticationService.handleLogin(loginRequest);
        if (jwtResponse != null) {
            return ResponseEntity.ok(
                    new BaseResponse(jwtResponse)
            );
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        var error = authenticationService.handleSignup(signUpRequest);
        if (error == ErrCode.NONE) {
            return ResponseEntity.ok(
                    new BaseResponse("Account has been created successfully")
            );
        }
        else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(error, "Account's creation failed !")
            );
        }
    }
}