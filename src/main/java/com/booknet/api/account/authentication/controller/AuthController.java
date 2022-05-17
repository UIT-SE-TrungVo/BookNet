package com.booknet.api.account.authentication.controller;

import com.booknet.api.account.authentication.payload.request.LoginRequest;
import com.booknet.api.account.authentication.payload.request.SignupRequest;
import com.booknet.api.account.authentication.payload.request.SignupVerifyRequest;
import com.booknet.api.account.authentication.service.AuthService;
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
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        var jwtResponse = authService.handleLogin(loginRequest);
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
        var error = authService.createNewUser(signUpRequest);
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

    @PostMapping("/signup/submit-verification")
    public ResponseEntity<?> verifyAccount(@Valid @RequestBody SignupVerifyRequest req) {
        var error = authService.verifyAccount(req);
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