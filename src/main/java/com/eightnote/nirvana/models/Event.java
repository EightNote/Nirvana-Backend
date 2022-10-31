package com.eightnote.nirvana.models;

import java.sql.Date;
import java.sql.Time;

public class Event {
    private int id;
    private Date date;
    private Time time;
    private String venue;
    private String registrationLink;
    private String eventPoster;
    private String artistID;
    private int countryID;

    public Event(int id, Date date, Time time, String venue, String registration, String eventPoster, String artistID, int countryID) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.registrationLink = registration;
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

    public String getRegistrationLink() {
        return registrationLink;
    }

    public String getEventPoster() {
        return eventPoster;
    }

    public String getArtistID() {
        return artistID;
    }

    public int getCountryID() {
        return countryID;
    }
}
