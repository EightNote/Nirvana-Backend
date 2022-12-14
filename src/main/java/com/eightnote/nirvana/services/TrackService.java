package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.TrackDAO;
import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.models.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackService {
    @Autowired
    private final TrackDAO trackDao;


    public TrackService(TrackDAO trackDao) {
        this.trackDao = trackDao;
    }

    public List<Track> getTrack(String trackName) {
        return trackDao.getTrack(trackName);
    }

    public void createTrack(Track track) {
        trackDao.createTrack(track);
    }

    public Album getAlbum(String track) {
        return trackDao.getAlbum(track);
    }

    public List<Track> getAllTrack() {
        return trackDao.getAllTrack();
    }

    public String getArtist(String track) {
        return trackDao.getAlbum(track).getArtist_id();
    }

    public List<String> getLikes(String track) {
        return trackDao.getLikes(track);
    }

    public boolean isLikedBy(String track, String username) {
        return trackDao.isLikedBy(track, username) > 0;
    }

    public void toggleLike(String likedByUsername, String trackName) {
        boolean hasUserLiked = isLikedBy(trackName, likedByUsername);
        trackDao.toggleLike(likedByUsername, trackName, hasUserLiked);
    }

    public List<Track> likedTracks(String username) {
        return trackDao.likedTracks(username);
    }
}
