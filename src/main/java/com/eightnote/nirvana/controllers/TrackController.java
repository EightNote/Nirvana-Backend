package com.eightnote.nirvana.controllers;


import com.eightnote.nirvana.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class TrackController {
    @Autowired
    private final TrackService trackService;

    public TrackController(TrackService trackService) {this.trackService = trackService;}

    @GetMapping("/track/{track}")
    public ResponseEntity track(
            @PathVariable("track") String track
    ){
        return new ResponseEntity<>(trackService.getTrack(track), HttpStatus.OK);
    }
}
