package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.PlaylistDAO;
import com.eightnote.nirvana.models.Playlist;
import com.eightnote.nirvana.models.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaylistService {
    @Autowired
    private final PlaylistDAO playlistDAO;

    public PlaylistService(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    public List<Track> getTracks(String playlistName) {
        return playlistDAO.getTracks(playlistName);
    }


    public Playlist getPlaylist(String playlistName) {
        return playlistDAO.getPlaylist(playlistName);
    }

    public List<String> getParticipants(String playlistName) {
        return playlistDAO.getParticipants(playlistName);
    }

    public void createUserPlaylist(String name, String desc, String type, String visibility, String createdByUser) {
        playlistDAO.createPlaylist(name, desc, type, visibility, createdByUser, null);
    }

    public void createArtistPlaylist(String name, String desc, String type, String visibility, String createdByArtist) {
        playlistDAO.createPlaylist(name, desc, type, visibility, null, createdByArtist);
    }

    public void updatePlaylistDescription(String playlistName, String description) {
        playlistDAO.updatePlaylistDescription(playlistName, description);
    }

    public void updatePlaylistVisibility(String playlistName, String visibility) {
        playlistDAO.updatePlaylistVisibility(playlistName, visibility);
    }

    public void deletePlaylist(String playlistName) {
        playlistDAO.deletePlaylist(playlistName);
    }

    public boolean containsTrack(String playlistName, String trackName) {
        return playlistDAO.getTracks(playlistName).contains(trackName);
    }

    public List<String> getLikes(String playlistName) {
        return playlistDAO.getLikes(playlistName);
    }

    public void like(String username, String playlistName) {
        playlistDAO.toggleLike(username, playlistName);
    }

    public String trackAddedBy(String playlistName, String trackName) {
        return playlistDAO.trackAddedBy(playlistName, trackName);
    }

    public String getOwner(String playlistName) {
        return playlistDAO.getOwner(playlistName);
    }
}