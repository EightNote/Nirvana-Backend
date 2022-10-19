package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/get-track-ids/{playlist}")
    public ResponseEntity getTrackIDs(@PathVariable("playlist") String playlistName) {
        return new ResponseEntity<>(playlistService.getTrackIDs(playlistName), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity getPlaylist(@RequestParam("playlistName") String playlistName){
        return new ResponseEntity<>(playlistService.getPlaylist(playlistName), HttpStatus.OK);
    }

    @GetMapping("/get-user-ids/{playlist}")
    public ResponseEntity getUserNames(@PathVariable("playlist") String playlistName) {
        return new ResponseEntity<>(playlistService.getUserName(playlistName), HttpStatus.OK);
    }
}
