package com.booknet.api.profile.event;

import com.booknet.api.authentication.model.AppUser;

public class ProfileCreateEventData {
    AppUser user;
    String firstName;
    String lastName;

    public ProfileCreateEventData() {
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
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
