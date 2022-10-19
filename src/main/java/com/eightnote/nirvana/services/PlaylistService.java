package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.PlaylistDAO;
import com.eightnote.nirvana.models.Playlist;
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

    public List<Integer> getTrackIDs(String playlistName) {
        return playlistDAO.getTrackIDs(playlistName);
    }


    public Playlist getPlaylist(String playlistName) {
        return playlistDAO.getPlaylist(playlistName);
    }

    public List<String> getUserName(String playlistName) {
        return playlistDAO.getUserNames(playlistName);
    }
}
