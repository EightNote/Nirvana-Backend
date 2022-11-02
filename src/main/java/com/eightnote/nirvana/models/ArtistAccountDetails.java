package com.eightnote.nirvana.models;

public class ArtistAccountDetails extends ArtistDetails {
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private  String password;

    public ArtistAccountDetails(String username, String email, String about, String twitter, String facebook, String instagram, int record_label_id, int nationality_id) {
        super(username, email, about, twitter, facebook, instagram, record_label_id, nationality_id);
    }
}
