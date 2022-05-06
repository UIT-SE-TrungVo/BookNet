package com.booknet.api.reset_password.service;

import com.booknet.base.payload.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class ResetPasswordService {

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            HttpServletRequest req
            , @RequestParam("email") String userEmail
    ) {
        return ResponseEntity.ok(
                new BaseResponse("OK")
        );
    }
}
