package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@RequestMapping("/event")
public class EventController {

    @Autowired
    private final GenreService eventController;

    public EventController(GenreService eventController) {
        this.eventController = eventController;
    }
}
