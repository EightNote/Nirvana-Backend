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

    public List<Track> getTrack(String trackName) { return trackDao.getTrack(trackName); }

    public void createTrack(Track track){trackDao.createTrack(track);}

    public Album getAlbum(String track) {
         return trackDao.getAlbum(track);
    }

    public List<Track> getAllTrack(){return trackDao.getAllTrack();}

    public String getArtist(String track) {
        return  trackDao.getAlbum(track).getArtist_id();
    }

    public List<String> getLikes(String track) {
        return  trackDao.getLikes(track);
    }

    public boolean isLikedBy(String track, String username) {
        return  trackDao.getLikes(track).contains(username);
    }

    public boolean hasLiked(String username,  String trackName) {
        return getLikes(trackName).contains(username);
    }
    public void toggleLike(String likedByUsername, String trackName) {
        boolean hasUserLiked = hasLiked(likedByUsername, trackName);
        trackDao.toggleLike(likedByUsername, trackName, hasUserLiked);
    }

    public List<Track> likedTracks(String username){
        return trackDao.likedTracks(username);
    }
}
