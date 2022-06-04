package com.booknet.api.account.authentication.model;

import com.booknet.api.profile.model.ProfileModel;
import com.booknet.constants.IdPrefix;
import com.booknet.utils.IdGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class AppUser {
    @Id
    protected String _id;

    @NotBlank
    @Size(max = 20)
    protected String username;

    @NotBlank
    @Size(max = 50)
    @Email
    protected String email;

    @NotBlank
    @Size(max = 120)
    protected String password;

    private Set<AppRole> roles = new HashSet<>();

    @DocumentReference(lazy = true, collection = "profiles")
    private ProfileModel profile;

    public AppUser() {
        this._id = IdGenerator.createNew(IdPrefix.USER);
    }

    public AppUser(String username, String email, String password) {
        this._id = IdGenerator.createNew(IdPrefix.USER);
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public AppUser(VerifyingUser verifyingUser) {
        this._id = verifyingUser.get_id();
        this.username = verifyingUser.getUsername();
        this.email = verifyingUser.getEmail();
        this.password = verifyingUser.getPassword();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }

    public ProfileModel getProfile() {
        return profile;
    }

    public void setProfile(ProfileModel profile) {
        this.profile = profile;
    }
}