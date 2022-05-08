package com.booknet.api.account.reset_password.controller;

import com.booknet.api.account.reset_password.payload.ResetPasswordSubmitTokenRequest;
import com.booknet.api.account.reset_password.service.ResetPasswordService;
import com.booknet.base.payload.BaseResponse;
import com.booknet.constants.ErrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class ResetPasswordController {
    @Autowired
    ResetPasswordService resetPasswordService;

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String userEmail) {
        var error = resetPasswordService.handleResetRequest(userEmail);
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

    @PostMapping("/reset-password/submit-token/")
    public ResponseEntity<?> submitResetToken(
            @Valid @RequestBody ResetPasswordSubmitTokenRequest req
    ) {
        return ResponseEntity.ok(
                new BaseResponse()
        );
    }
}