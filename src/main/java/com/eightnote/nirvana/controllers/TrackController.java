package com.eightnote.nirvana.controllers;


import com.eightnote.nirvana.models.Track;
import com.eightnote.nirvana.services.NirvanaUserService;
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
    @Autowired
    private final NirvanaUserService nirvanaUserService;

    public TrackController(TrackService trackService, NirvanaUserService nirvanaUserService) {this.trackService = trackService;
        this.nirvanaUserService = nirvanaUserService;
    }

    @GetMapping("/{track}")
    public ResponseEntity<?> track(
            @PathVariable("track") String track
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication.getName());
        return new ResponseEntity<>(trackService.getTrack(track), HttpStatus.OK);
    }

    @PostMapping("/toggle-like/")
    public void likes(@RequestParam("playlist") String trackName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        trackService.toggleLike(authentication.getName(), trackName);
    }

    @PostMapping("/")
    public void createTrack(@RequestBody Track track) {
        trackService.createTrack(track);
    }

    @GetMapping("/get-album/{track}")
    public ResponseEntity<?> getAlbum(
            @PathVariable("track") String track
    ){
        trackService.getAlbum(track);
        return new ResponseEntity<>(trackService.getAlbum(track), HttpStatus.OK);
    }

    @GetMapping("/get-artist/{track}")
    public ResponseEntity<?> getArtist(
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
    public ResponseEntity<?> isLikedBy(
            @RequestParam("track") String track,
            @RequestParam("username") String username
    ){
        trackService.isLikedBy(track, track);
        return new ResponseEntity<>(trackService.isLikedBy(username, track), HttpStatus.OK);
    }
}
