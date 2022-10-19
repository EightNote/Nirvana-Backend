package com.eightnote.nirvana.models;

import org.springframework.stereotype.Component;

public class ArtistPhotos {
    private final int srno;
    private final String url;
    private final String description;

    public ArtistPhotos(int srno, String url, String description) {
        this.srno = srno;
        this.url = url;
        this.description = description;
    }

    public int getSrno() {
        return srno;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
