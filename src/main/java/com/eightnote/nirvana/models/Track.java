package com.eightnote.nirvana.models;

public class Track {
    private final int id;

    private final String title;

    private final String audio_file;

    private final int track_length;

    private final Boolean explicit_content;

    private final String writer;

    private final String composer;

    private final String producer;

    private final String lyrics;

    private final int album_id;


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
