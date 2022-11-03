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

    public String getAlbum_title() {
        return album_title;
    }
    public int getArtist_id() {
        return artist_id;
    }

    public int getGenre_id() {
        return genre_id;
    }
    public String getAlbum_logo() {
        return album_logo;
    }


    public void setId(int id) {
        this.id = id;
    }
}
