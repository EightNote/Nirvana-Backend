package com.eightnote.nirvana.models;

import org.springframework.stereotype.Component;

public class Playlist {
    private int id;
    private String name;
    private String description;
    private String type;
    private boolean visibility;
    private String createdByUser;
    private String createdByArtist;


    public Playlist(int id, String name, String description, String type, boolean visibility, String createdByUser, String createdByArtist) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.visibility = visibility;
        this.createdByUser = createdByUser;
        this.createdByArtist = createdByArtist;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public String getCreatedByArtist() {
        return createdByArtist;
    }
}
