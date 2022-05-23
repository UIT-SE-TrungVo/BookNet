package com.booknet.api.account.reset_password.controller;

import com.booknet.api.account.reset_password.payload.ResetPasswordRenewRequest;
import com.booknet.api.account.reset_password.payload.ResetPasswordSubmitTokenRequest;
import com.booknet.api.account.reset_password.service.ResetPasswordService;
import com.booknet.base.payload.BaseResponse;
import com.booknet.constants.ErrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reset-password")
public class ResetPasswordController {
    @Autowired
    ResetPasswordService resetPasswordService;

    @PostMapping("/request-token/email={email}")
    public ResponseEntity<?> requestToken(
            @PathVariable("email") String userEmail
    ) {
        var error = resetPasswordService.handleRequestToken(userEmail);
        if (error == ErrCode.NONE) {
            return ResponseEntity.ok(
                    new BaseResponse()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(error, null)
            );
        }
    }

    @PostMapping("/submit-token")
    public ResponseEntity<?> submitResetToken(
            @Valid @RequestBody ResetPasswordSubmitTokenRequest req
    ) {
        var error = resetPasswordService.handleSubmitToken(req);
        if (error == ErrCode.NONE) {
            return ResponseEntity.ok(
                    new BaseResponse()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(error, null)
            );
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> submitChangePassword(
            @Valid @RequestBody ResetPasswordRenewRequest req
    ) {
        var error = resetPasswordService.handleChangePassword(req);
        if (error == ErrCode.NONE) {
            return ResponseEntity.ok(
                    new BaseResponse()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(error, null)
            );
        }
    }
}