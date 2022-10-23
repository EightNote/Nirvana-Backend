package com.eightnote.nirvana.models;

import org.springframework.stereotype.Component;

import java.util.Date;

public class ArtistPhotos {
    private final Date date;
    private final String url;

    public ArtistPhotos(String url, Date date) {
        this.date = date;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
