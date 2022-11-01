package com.eightnote.nirvana.models;

public class ArtistDetails {
    private String username;
    private String email;
    private String about;
    private String twitter;
    private String facebook;
    private String instagram;
    private int record_label_id;
    private int nationality_id;

    public ArtistDetails(String username, String email, String about, String twitter, String facebook, String instagram, int record_label_id, int nationality_id) {
        this.username = username;
        this.email = email;
        this.about = about;
        this.twitter = twitter;
        this.facebook = facebook;
        this.instagram = instagram;
        this.record_label_id = record_label_id;
        this.nationality_id = nationality_id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAbout() {
        return about;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public int getRecord_label_id() {
        return record_label_id;
    }

    public int getNationality_id() {
        return nationality_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setRecord_label_id(int record_label_id) {
        this.record_label_id = record_label_id;
    }

    public void setNationality_id(int nationality_id) {
        this.nationality_id = nationality_id;
    }
}
