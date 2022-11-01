package com.eightnote.nirvana.models;

public class Album {
    private int id;
    private String artistId;
    private int likeCount;

    private String albumLogoUrl;
    private String albumTitle;
    private String genre;

    public Album(int id, String artistId, int likeCount, String albumLogoUrl, String albumTitle, String genre) {
        this.id = id;
        this.artistId = artistId;
        this.likeCount = likeCount;
        this.albumLogoUrl = albumLogoUrl;
        this.albumTitle = albumTitle;
        this.genre = genre;
    }


    public int getId() {
        return id;
    }

    public String getArtistId() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setAlbumLogoUrl(String albumLogoUrl) {
        this.albumLogoUrl = albumLogoUrl;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
