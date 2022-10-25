package com.eightnote.nirvana.models;

public class PlaylistTrackInfo extends Track {
    private final String trackAddedBy;

    public PlaylistTrackInfo(int id, String title, String audio_file, int track_length, Boolean explicit_content, String writer, String composer, String producer, String lyrics, int album_id, String trackAddedBy) {
        super(id, title, audio_file, track_length, explicit_content, writer, composer, producer, lyrics, album_id);
        this.trackAddedBy = trackAddedBy;
    }

    public String getTrackAddedBy() {
        return trackAddedBy;
    }

}