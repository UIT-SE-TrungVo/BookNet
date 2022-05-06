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

import java.util.UUID;

@Controller
@RequestMapping("/user")
public class ResetPasswordController {
    @Autowired
    ResetPasswordService resetPasswordService;

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String userEmail) {
        var user = resetPasswordService.getUserByEmail(userEmail);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(ErrCode.USER_NOT_FOUND_WITH_EMAIL, null)
            );
        } else {
            var token = UUID.randomUUID().toString();
            resetPasswordService.createPasswordResetTokenForUser(user.get(), token);
            return ResponseEntity.ok(
                    new BaseResponse("OK")
            );
        }
    }
}