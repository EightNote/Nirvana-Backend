package com.eightnote.nirvana.models;

public class Track {
    private int id;

    private String title;

    private String audio_file;

    private int track_length;

    private Boolean explicit_content;

    private String writer;

    private String composer;

    private String producer;

    private String lyrics;

    private int album_id;


    public Track(int id, String title, String audio_file, int track_length, Boolean explicit_content, String writer, String composer, String producer, String lyrics, int album_id) {
        this.id = id;
        this.title = title;
        this.audio_file = audio_file;
        this.track_length = track_length;
        this.explicit_content = explicit_content;
        this.writer = writer;
        this.composer = composer;
        this.producer = producer;
        this.lyrics = lyrics;
        this.album_id = album_id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAudio_file() {
        return audio_file;
    }

    public int getTrack_length() {
        return track_length;
    }

    public Boolean getExplicit_content() {
        return explicit_content;
    }

    public String getWriter() {
        return writer;
    }

    public String getComposer() {
        return composer;
    }

    public String getProducer() {
        return producer;
    }

    public String getLyrics() {
        return lyrics;
    }

    public int getAlbum_id() {
        return album_id;
    }
}
