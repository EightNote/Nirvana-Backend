package com.eightnote.nirvana.models;

import java.net.URL;
import java.sql.Date;

public class RecordLabel {
    private int id;
    private String username;
    private String email;
    private String password;
    private  boolean is_active;
    private Date created_at;
    private Date updated_at;

    private String labelName;
    private String description;
    private URL logo;

    private URL twitter;
    private URL facebook;
    private URL instagram;

    public RecordLabel(int id, String username, String email, String password, boolean is_active, Date created_at, Date updated_at, String labelName, String description, URL logo, URL twitter, URL facebook, URL instagram) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.is_active = is_active;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.labelName = labelName;
        this.description = description;
        this.logo = logo;
        this.twitter = twitter;
        this.facebook = facebook;
        this.instagram = instagram;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLogo(URL logo) {
        this.logo = logo;
    }

    public void setTwitter(URL twitter) {
        this.twitter = twitter;
    }

    public void setFacebook(URL facebook) {
        this.facebook = facebook;
    }

    public void setInstagram(URL instagram) {
        this.instagram = instagram;
    }
}
