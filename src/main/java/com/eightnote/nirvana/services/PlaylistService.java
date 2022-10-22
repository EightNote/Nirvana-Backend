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

    public List<String> getUserName(String playlistName) {
        return playlistDAO.getUserNames(playlistName);
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
        return playlistDAO.containsTrack(playlistName, trackName);
    }

    public List<String> getLikes(String playlistName) {
        return playlistDAO.getLikes(playlistName);
    }
}