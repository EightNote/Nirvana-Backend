package com.eightnote.nirvana.models;

public class PlaylistTrackInfo extends Track {
    private String trackAddedBy;

    public PlaylistTrackInfo(String title, String audio_file, int track_length, Boolean explicit_content, String writer, String composer, String producer, String lyrics, int album_id) {
        super(title, audio_file, track_length, explicit_content, writer, composer, producer, lyrics, album_id);
    }

    public String getTrackAddedBy() {
        return trackAddedBy;
    }

    public void setTrackAddedBy(String trackAddedBy) {
        this.trackAddedBy = trackAddedBy;
    }
}
