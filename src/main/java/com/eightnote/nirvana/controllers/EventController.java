package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.models.Event;
import com.eightnote.nirvana.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Component
@CrossOrigin
@RequestMapping("events/")
public class EventController {
    @Autowired
    private final EventService eventService;

    public EventController(EventService eventController) {
        this.eventService = eventController;
    }

    @PostMapping("")
    public void create(@RequestBody Event e) {
        eventService.create(e.getDate(), e.getTime(), e.getVenue(), e.getRegistrationLink(), e.getEventPoster(), e.getArtistID(), e.getCountryID());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") String id) {
        Event e = eventService.getEvent(id);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @GetMapping("all/")
    public ResponseEntity<?> getAllEvents() {
        List<Event> e = eventService.getAllEvents();
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @GetMapping("active/")
    public ResponseEntity<?> getActiveEvents() throws ParseException {
        var eList = eventService.getAllEvents();
        var activeList = new ArrayList<Event>();
        for (Event e : eList) {

            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(e.getDate());
            if (date1.compareTo(new Date()) > 0) {
                activeList.add(e);
            }
        }

        return new ResponseEntity<>(activeList, HttpStatus.OK);
    }

    @PutMapping("")
    public void updateEvent(@RequestBody Event event) {
        eventService.updateEventTime(event.getRegistrationLink(), event.getTime());
        eventService.updateEventDate(event.getRegistrationLink(), event.getDate());
        eventService.updateEventVenue(event.getRegistrationLink(), event.getVenue());
    }
}

