package com.eightnote.nirvana.models;

import org.springframework.stereotype.Component;

public class Album {
    private final int id;
    private final int artistId;
    private final int likeCount;
    private final String albumTitle;
    private final String genre;

    public Album(int id, int artistId , String albumTitle, String genre, int likeCount) {
        this.id = id;
        this.artistId = artistId;
        this.albumTitle = albumTitle;
        this.genre = genre;
        this.likeCount = likeCount;
    }

    public int getId() {
        return id;
    }

    public int getArtistId() {
        return artistId;
    }

    public String getGenreId() {
        return genre;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getGenre() {
        return genre;
    }

    public int getLikes() {
        return this.likeCount;
    }
}
