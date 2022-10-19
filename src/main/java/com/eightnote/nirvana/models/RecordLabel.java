package com.eightnote.nirvana.models;

import java.net.URL;
import java.sql.Date;

public class RecordLabel {
    private final int id;
    private final String username;
    private final String email;
    private final String password;
    private  final boolean is_active;
    private final Date created_at;
    private final Date updated_at;

    private final String labelName;
    private final String description;
    private final URL logo;

    private final URL twitter;
    private final URL facebook;
    private final URL instagram;

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
}
