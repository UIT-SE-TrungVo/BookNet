package com.booknet.apis.authentication.payload.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class JwtResponse {
    private String jwt;

    private String id;

    @NotBlank
    @Size(max = 32)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    List<String> roles;

    public JwtResponse(String jwt, String id, String username, String email, List<String> roles) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
