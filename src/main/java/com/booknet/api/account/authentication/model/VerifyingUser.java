package com.booknet.api.account.authentication.model;

import com.booknet.api.account.authentication.config.AuthConfig;
import com.booknet.utils.Utils;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Document(collection = "verifying_user")
public class VerifyingUser extends AppUser {

    @NotEmpty
    private String token;

    private Date expiryDate;

    public VerifyingUser(String username, String email, String password) {
        super(username, email, password);

        var expiration = AuthConfig.VERIFY_TOKEN_EXPIRATION_IN_SECONDS;
        var expiryDate = Utils.time.getExpiryDateFromNow(expiration);
        this.setExpiryDate(expiryDate);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}