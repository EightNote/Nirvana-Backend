package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.PlaylistDAO;
import com.eightnote.nirvana.models.Playlist;
import com.eightnote.nirvana.models.PlaylistTrackInfo;
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

    public List<PlaylistTrackInfo> getTracks(String username, String playlistName) {
        return playlistDAO.getTracks(getPlaylistID(username, playlistName));
    }

    public List<PlaylistTrackInfo> getTracks(int playlistID) {
        return playlistDAO.getTracks(playlistID);
    }

    public int getPlaylistID(String username, String playlistName) {
        return playlistDAO.getPlaylistID(username, playlistName);
    }


    public Playlist getPlaylist(String username, String playlistName) {
        return playlistDAO.getPlaylist(getPlaylistID(username, playlistName));
    }

    public List<String> getParticipants(String username, String playlistName) {
        return playlistDAO.getParticipants(getPlaylistID(username, playlistName));
    }

    public void createUserPlaylist(String name, String desc, String type, String visibility, String createdByUser) {
        playlistDAO.createPlaylist(name, desc, type, visibility, createdByUser, null);
    }

    public void createArtistPlaylist(String name, String desc, String type, String visibility, String createdByArtist) {
        playlistDAO.createPlaylist(name, desc, type, visibility, null, createdByArtist);
    }

    public void updatePlaylistDescription(String username, String playlistName, String description) {
        playlistDAO.updatePlaylistDescription(getPlaylistID(username, playlistName), description);
    }

    public void updatePlaylistVisibility(String username, String playlistName, String visibility) {
        playlistDAO.updatePlaylistVisibility(getPlaylistID(username, playlistName), visibility);
    }

    public void deletePlaylist(String username, String playlistName) {
        playlistDAO.deletePlaylist(getPlaylistID(username, playlistName));
    }

//    public boolean containsTrack(String username, String playlistName, String trackName) {
//        return playlistDAO.getTracks(getPlaylistID(username, playlistName)).contains(trackName);
//    }

    public List<String> getLikes(String username, String playlistName) {
        return playlistDAO.getLikes(getPlaylistID(username, playlistName));
    }

    public List<String> getLikes(int playlistID) {
        return playlistDAO.getLikes(playlistID);
    }

    public void toggleLike(String ownerUsername, String likedByUsername, String playlistName) {
        int playlistID = getPlaylistID(ownerUsername, playlistName);
        boolean hasUserLiked = hasLiked(likedByUsername, playlistID);
        playlistDAO.toggleLike(likedByUsername, playlistID, hasUserLiked);
    }

    public boolean hasLiked(String username,  int playlistID) {
        return getLikes(playlistID).contains(username);
    }

    public String trackAddedBy(String username, String playlistName, String trackName) {
        var tracks = playlistDAO.getTracks(getPlaylistID(username, playlistName));
        for (var track : tracks)
            if (track.getTitle().equals(trackName))
                return track.getTrackAddedBy();
        return "";
    }

    public String getOwner(String username, String playlistName) {
        int playlistID = getPlaylistID(username, playlistName);
        String userCreator = playlistDAO.getPlaylist(playlistID).getCreatedByUser();
        if (userCreator == null) return playlistDAO.getPlaylist(playlistID).getCreatedByArtist();
        return userCreator;
    }

    public boolean containsTrack(String ownerUsername, String playlistName, String trackName) {
        int playlistID = getPlaylistID(ownerUsername, playlistName);
        var track_list = getTracks(playlistID);
        for (PlaylistTrackInfo pti : track_list) {
            if (pti.getTitle().equals(trackName)) return true;
        }

        return false;
    }

    public List<Playlist> getPlaylistByUser(String username) {
        return playlistDAO.getPlaylistByUser(username);
    }
}