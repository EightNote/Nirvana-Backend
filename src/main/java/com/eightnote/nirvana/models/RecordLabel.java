package com.eightnote.nirvana.models;

import java.net.URL;
import java.sql.Date;

public class RecordLabel {
    private String username;
    private String name;
    private String description;
    private String logo;
    private String twitter;
    private String facebook;
    private String instagram;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public RecordLabel(String username, String description, String logo, String twitter, String facebook, String instagram) {
        this.username = username;
        this.description = description;
        this.logo = logo;
        this.twitter = twitter;
        this.facebook = facebook;
        this.instagram = instagram;
    }
}
