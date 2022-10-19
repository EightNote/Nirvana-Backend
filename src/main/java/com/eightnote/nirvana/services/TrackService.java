package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.TrackDAO;
import com.eightnote.nirvana.models.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackService {
    @Autowired
    private final TrackDAO trackDao;


    public TrackService(TrackDAO trackDao) {
        this.trackDao = trackDao;
    }

    public Track getTrack(String trackName) { return trackDao.getTrack(trackName); }

    public void createTrack(String trackName){trackDao.createTrack(trackName);}
}
