package com.eightnote.nirvana.services;
import com.eightnote.nirvana.DAOs.EventDAO;
import com.eightnote.nirvana.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class EventService {

    @Autowired
    private final EventDAO eventDAO;

    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public void create(String date, String time, String venue, String registrationLink, String posterUrl, String artistName, int country) {
        eventDAO.create(date, time, venue, registrationLink, posterUrl, artistName, country);
    }

    public Event getEvent(int id) {
        return eventDAO.getEvent(id);
    }

    public List<Event> getAllEvents() {
        return eventDAO.getAllEvents();
    }

    public void updateEventTime(String reg, String time) {
        eventDAO.updateEventTime(reg, time);
    }

    public void updateEventDate(String reg, String date) {
        eventDAO.updateEventDate(reg, date);
    }

    public void updateEventVenue(String reg, String venue) {
        eventDAO.updateEventVenue(reg, venue);
    }
}