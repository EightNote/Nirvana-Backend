package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.models.Event;
import com.eightnote.nirvana.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Component
@CrossOrigin
@RequestMapping("/event")
public class EventController {

    @Autowired
    private final EventService eventService;

    public EventController(EventService eventController) {
        this.eventService = eventController;
    }

    @PostMapping("/")
    public void create(
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            @RequestParam("venue") String venue,
            @RequestParam(value = "reg_url",required = false) String registrationLink,
            @RequestParam("poster_url") String posterUrl,
            @RequestParam("artist") String artistName,
            @RequestParam("country") String country
            ) {
        eventService.create(date, time, venue, registrationLink, posterUrl, artistName, country);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEvent(@PathVariable("id") int id) {
        Event e = eventService.getEvent(id);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @GetMapping("/all/")
    public ResponseEntity getAllEvents() {
        List<Event> e = eventService.getAllEvents();
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @GetMapping("active")
    public ResponseEntity getActiveEvents() {
        var eList = eventService.getAllEvents();
        var activeList = new ArrayList<Event>();
        for (Event e : eList) {
            if (LocalDateTime.now().isBefore(e.getDate().toLocalDate().atTime(e.getTime().toLocalTime()))) {
                activeList.add(e);
            }
        }

        return new ResponseEntity<>(activeList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public void updateEvent(
        @PathVariable("id") int id,
        @RequestParam("date") String date,
        @RequestParam("time") String time,
        @RequestParam("venue") String venue
    ) {
        if (time != null) eventService.updateEventTime(id, time);
        if (date != null) eventService.updateEventDate(id, date);
        if (venue != null) eventService.updateEventVenue(id, venue);
    }
}

