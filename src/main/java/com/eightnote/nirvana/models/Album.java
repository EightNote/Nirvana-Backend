package com.eightnote.nirvana.models;

public class Album {
    private int id;
    private String album_logo;
    private String album_title;
    private int genre_id;
    private int artist_id;



    public Album(int id, int artistId, String albumLogoUrl, String albumTitle, int genre) {
        this.id = id;
        this.artist_id = artistId;
        this.album_logo = albumLogoUrl;
        this.album_title = albumTitle;
        this.genre_id = genre;
    }


    public int getId() {
        return id;
    }

    public int getArtistId() {
        return artist_id;
    }

    public int getGenreId() {
        return genre_id;
    }

    public String getAlbumTitle() {
        return album_title;
    }

    public int getGenre() {
        return genre_id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setArtistId(int artistId) {
        this.artist_id = artistId;
    }


    public void setAlbumLogoUrl(String albumLogoUrl) {
        this.album_logo = albumLogoUrl;
    }

    public void setAlbumTitle(String albumTitle) {
        this.album_title = albumTitle;
    }

    public void setGenre(int genre) {
        this.genre_id = genre;
    }
}
