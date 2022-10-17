package com.eightnote.nirvana.models;

public class Album {
    private final int id;
    private final int artistId;
    private final String albumTitle;
    private final String genre;

    public Album(int id, int artistId , String albumTitle, String genre) {
        this.id = id;
        this.artistId = artistId;
        this.albumTitle = albumTitle;
        this.genre = genre;
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
}
