package com.eightnote.nirvana.models;

import java.sql.Date;
import java.sql.Time;

public class Event {
    private final int id;
    private  final Date date;
    private final Time time;
    private final String venue;
    private final String registration;
    private final String eventPoster;
    private final int artistID;
    private final int countryID;


    public Event(int id, Date date, Time time, String venue, String registration, String eventPoster, int artistID, int countryID) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.registration = registration;
        this.eventPoster = eventPoster;
        this.artistID = artistID;
        this.countryID = countryID;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public String getRegistration() {
        return registration;
    }

    public String getEventPoster() {
        return eventPoster;
    }

    public int getArtistID() {
        return artistID;
    }

    public int getCountryID() {
        return countryID;
    }
}
