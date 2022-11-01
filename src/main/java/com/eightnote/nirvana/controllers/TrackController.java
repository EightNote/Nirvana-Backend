package com.eightnote.nirvana.controllers;


import com.eightnote.nirvana.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@CrossOrigin
@RequestMapping("tracks/")
public class TrackController {
    @Autowired
    private final TrackService trackService;

    public TrackController(TrackService trackService) {this.trackService = trackService;}

    @GetMapping("/{track}")
    public ResponseEntity track(
            @PathVariable(name = "track", required = false) String track
    ){
        System.out.println(track);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(track.equals("all")){
            return new ResponseEntity<>(trackService.getAllTrack(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(trackService.getTrack(track), HttpStatus.OK);
        }

    }

    @PostMapping("/toggle-like/")
    public void likes(@RequestParam("playlist") String trackName) {
        String likedByUsername = ""; // TODO: Get Username
        trackService.toggleLike(likedByUsername, trackName);
    }

    @GetMapping("/get-album/{track}")
    public ResponseEntity getAlbum(
            @PathVariable("track") String track
    ){
        trackService.getAlbum(track);
        return new ResponseEntity<>(trackService.getAlbum(track), HttpStatus.OK);
    }

    @GetMapping("/get-artist/{track}")
    public ResponseEntity getArtist(
            @PathVariable("track") String track
    ){
        trackService.getArtist(track);
        return new ResponseEntity<>(trackService.getArtist(track), HttpStatus.OK);
    }

    @GetMapping("/get-likes/{track}")
    public ResponseEntity<?> getLikes(
            @PathVariable("track") String track
    ){
        trackService.getLikes(track);
        return new ResponseEntity<>(trackService.getLikes(track), HttpStatus.OK);
    }

    @GetMapping("/is-liked-by/")
    public ResponseEntity isLikedBy(
            @RequestParam("track") String track,
            @RequestParam("username") String username
    ){
        trackService.isLikedBy(track, track);
        return new ResponseEntity<>(trackService.isLikedBy(username, track), HttpStatus.OK);
    }
}
