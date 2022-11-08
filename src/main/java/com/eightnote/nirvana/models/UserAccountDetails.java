package com.eightnote.nirvana.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserAccountDetails extends UserDetails {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private List<Integer> interests;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserAccountDetails(String username, String firstName, String lastName, String dateOfBirth, String gender, List<Integer> interests, String password) {
        super(username, firstName, lastName, dateOfBirth, gender, null);
        this.password = password;
        this.interests = interests;
    }

    public UserAccountDetails encrypted() {
        UserAccountDetails encrypted = this;
        encrypted.setPassword(bCryptPasswordEncoder.encode(this.password));
        return encrypted;
    }
}
