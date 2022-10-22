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

    public Track getTrack(String trackName) { return trackDao.getTrack(trackName); }

    public void createTrack(String trackName){trackDao.createTrack(trackName);}

    public Album getAlbum(String track) {
         return trackDao.getAlbum(track);
    }

    public String getArtist(String track) {
        return  trackDao.getArtist(track);
    }

    public List<String> getLikes(String track) {
        return  trackDao.getLikes(track);
    }

    public boolean isLikedBy(String track, String username) {
        return  trackDao.isLikedBy(username, track);
    }
}
