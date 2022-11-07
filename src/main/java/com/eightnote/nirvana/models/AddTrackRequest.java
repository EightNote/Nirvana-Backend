package com.eightnote.nirvana.models;

import java.util.List;

public class AddTrackRequest {
    private int playlistID;
    private String addedByID;

    public String getAddedByID() {
        return addedByID;
    }

    public void setAddedByID(String addedByID) {
        this.addedByID = addedByID;
    }

    private List<String> trackList;

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public List<String> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<String> trackList) {
        this.trackList = trackList;
    }
}
