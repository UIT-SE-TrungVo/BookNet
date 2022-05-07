package com.booknet.api.account.reset_password.controller;

import com.booknet.api.account.reset_password.service.ResetPasswordService;
import com.booknet.base.payload.BaseResponse;
import com.booknet.constants.ErrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class ResetPasswordController {
    @Autowired
    ResetPasswordService resetPasswordService;

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String userEmail) {
        var error = resetPasswordService.handlePasswordReset(userEmail);
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