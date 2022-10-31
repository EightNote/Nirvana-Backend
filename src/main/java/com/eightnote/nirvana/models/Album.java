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
}
