package com.booknet.api.guild.controller;

import com.booknet.api.guild.request.GuildJoinRequest;
import com.booknet.api.guild.request.GuildLeaveRequest;
import com.booknet.api.guild.request.GuildViewRequest;
import com.booknet.api.guild.service.GuildService;
import com.booknet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/guilds")
public class GuildController {

    @Autowired
    GuildService guildService;

    @GetMapping
    public ResponseEntity<?> getListGuild() {
        var response = guildService.getAllGuildInfo();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view")
    public ResponseEntity<?> getGuildInfo(@Valid @RequestBody GuildViewRequest req) {
        var response = guildService.getSpecificGuildInfo(req);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinGuild(@Valid @RequestBody GuildJoinRequest req) {
        var response = guildService.requestJoinGuild(req);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/leave")
    public ResponseEntity<?> leaveGuild(@Valid @RequestBody GuildLeaveRequest req) {
        var response = guildService.requestLeaveGuild(req);
        return ResponseEntity.ok(response);
    }

}