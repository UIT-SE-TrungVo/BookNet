package com.booknet.api.profile.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Document(collection = "user_profiles")
public class ProfileModel {
    @Id
    String _id;

    @NotEmpty
    @Size(max = 20)
    String firstName;

    @Size(max = 20)
    String lastName;

    public ProfileModel() {
    }

    public ProfileModel(String _id, String firstName, String lastName) {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
