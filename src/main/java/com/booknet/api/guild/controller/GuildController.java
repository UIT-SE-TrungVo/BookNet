package com.booknet.api.guild.controller;

import com.booknet.api.guild.request.GuildJoinRequest;
import com.booknet.api.guild.request.GuildLeaveRequest;
import com.booknet.base.payload.BaseResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/guild")
public class GuildController {

    @GetMapping
    public ResponseEntity<?> getListGuild() {
        return ResponseEntity.ok(
                new BaseResponse()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGuildInfo(@PathVariable("id") String guildId) {
        return ResponseEntity.ok(
                new BaseResponse()
        );
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinGuild(@Valid @RequestBody GuildJoinRequest req) {
        return ResponseEntity.ok(
                new BaseResponse()
        );
    }

    @PostMapping("/leave")
    public ResponseEntity<?> leaveGuild(@Valid @RequestBody GuildLeaveRequest req) {
        return ResponseEntity.ok(
                new BaseResponse()
        );
    }

}