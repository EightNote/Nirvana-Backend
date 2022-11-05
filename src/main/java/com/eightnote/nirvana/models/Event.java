package com.eightnote.nirvana.models;

public class Event {
    private String date;
    private String time;
    private String venue;
    private String registrationLink;
    private String eventPoster;
    private String artistID;
    private int countryID;

    public Event(String date, String time, String venue, String registration, String eventPoster, String artistID, int countryID) {
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.registrationLink = registration;
        this.eventPoster = eventPoster;
        this.artistID = artistID;
        this.countryID = countryID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setRegistrationLink(String registrationLink) {
        this.registrationLink = registrationLink;
    }

    public void setEventPoster(String eventPoster) {
        this.eventPoster = eventPoster;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
