package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.models.Playlist;
import com.eightnote.nirvana.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@RequestMapping("/playlist")
public class PlaylistController {
    private GrantedAuthority artistAuthority = () -> "ARTIST";
    private GrantedAuthority userAuthority = () -> "USER";

    @Autowired
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/get-tracks/{playlist}")
    public ResponseEntity getTrack(@PathVariable("playlist") String playlistName) {
        return new ResponseEntity<>(playlistService.getTracks(playlistName), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity getPlaylist(@RequestParam("playlistName") String playlistName){
        return new ResponseEntity<>(playlistService.getPlaylist(playlistName), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity createPlaylist(
            @RequestParam("name") String playlistName,
            @RequestParam("desc") String description,
            @RequestParam("type") String type,
            @RequestParam("vis") String visibility
            ){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();

        if (authentication.getAuthorities().contains(artistAuthority)) {
            playlistService.createArtistPlaylist(playlistName, description, type, visibility, loggedInUsername);
        }
        else if (authentication.getAuthorities().contains(userAuthority)) {
            playlistService.createUserPlaylist(playlistName, description, type, visibility, loggedInUsername);
        }

        Playlist playlist = playlistService.getPlaylist(playlistName);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @PutMapping("/update-desc/")
    public ResponseEntity<Playlist> updatePlaylistDescription(
            @RequestParam(name = "name", defaultValue = "NULL") String playlistName,
            @RequestParam(name = "desc", defaultValue = "NULL") String description
    ) {
        playlistService.updatePlaylistDescription(playlistName, description);

        Playlist playlist = playlistService.getPlaylist(playlistName);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @PutMapping("/update-visibility")
    public ResponseEntity updatePlaylistVisibility(
            @RequestParam(name = "name", defaultValue = "NULL") String playlistName,
            @RequestParam(name = "visibility", defaultValue = "NULL") String visibility
    ) {
        playlistService.updatePlaylistVisibility(playlistName, visibility);

        Playlist playlist = playlistService.getPlaylist(playlistName);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @DeleteMapping("/{playlist}")
    public ResponseEntity deletePlaylist(@PathVariable("playlist") String playlistName) {
        playlistService.deletePlaylist(playlistName);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/get-participants/{playlist}")
    public ResponseEntity getParticipants(@PathVariable("playlist") String playlistName) {
        return new ResponseEntity<>(playlistService.getUserName(playlistName), HttpStatus.OK);
    }

    @GetMapping("/contains-track")
    public ResponseEntity containsTrack(
            @RequestParam("playlist") String playlistName,
            @RequestParam("trackName") String trackName
    ){
        boolean doesContain = playlistService.containsTrack(playlistName, trackName);
        return new ResponseEntity<>(doesContain, HttpStatus.OK);
    }

    @GetMapping("/likes/{playlist}")
    public ResponseEntity getLikes(@PathVariable("playlist") String playlistName) {
        List<String> likes = playlistService.getLikes(playlistName);

        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @GetMapping("/like-count/{playlist}")
    public ResponseEntity getLikeCount(@PathVariable("playlist") String playlistName) {
        List<String> likes = playlistService.getLikes(playlistName);

        return new ResponseEntity<>(likes.size(), HttpStatus.OK);
    }

    @PostMapping("/like/")
    public void likes(@RequestParam("user") String username, @RequestParam("playlist") String playlistName) {
        playlistService.like(username, playlistName);
    }

    @GetMapping("/is-liked-by/")
    public ResponseEntity isLikedBy(@RequestParam("user") String username, @RequestParam("playlist") String playlistName) {
        List<String> likes = playlistService.getLikes(playlistName);
        boolean doesLike = likes.contains(username);

        return new ResponseEntity<>(doesLike, HttpStatus.OK);
    }

    @GetMapping("/track-added-by/{playlistName}/{trackName}/")
    public ResponseEntity trackAddedBy(
            @PathVariable("playlistName") String playlistName,
            @PathVariable("trackName") String trackName
    ) {
        String added_by = playlistService.trackAddedBy(playlistName, trackName);

        return new ResponseEntity<>(added_by, HttpStatus.OK);
    }

    @GetMapping("/is-participant/")
    public ResponseEntity isParticipant(@RequestParam("user") String username, @RequestParam("playlist") String playlistName) {
        List<String> participants = playlistService.getLikes(playlistName);
        boolean isParticipant = playlistService.getUserName(playlistName).contains(username);

        return new ResponseEntity<>(isParticipant, HttpStatus.OK);
    }

    @GetMapping("/owner/{playlist}")
    public ResponseEntity getOwner(@PathVariable("playlist") String playlistName) {
        String owner = playlistService.getOwner(playlistName);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }
}
