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
@CrossOrigin
@RequestMapping("/playlist")
public class PlaylistController {
    final private GrantedAuthority artistAuthority = () -> "ARTIST";
    final private GrantedAuthority userAuthority = () -> "USER";

    @Autowired
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/get-tracks/{playlist}")
    public ResponseEntity getTrack(@PathVariable("playlist") String playlistName, @RequestParam("ownerUsername") String ownerUsername) {
        return new ResponseEntity<>(playlistService.getTracks(ownerUsername, playlistName), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity getPlaylist(@RequestParam("playlistName") String playlistName, @RequestParam("ownerUsername") String ownerUsername){
        return new ResponseEntity<>(playlistService.getPlaylist(ownerUsername, playlistName), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity createPlaylist(
            @RequestParam("name") String playlistName,
            @RequestParam("ownerUsername") String ownerUsername,
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

        String username = ""; // TODO: Get Username
        Playlist playlist = playlistService.getPlaylist(ownerUsername, playlistName);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @PutMapping("/update-desc/")
    public ResponseEntity<Playlist> updatePlaylistDescription(
            @RequestParam(name = "name", defaultValue = "NULL") String playlistName,
            @RequestParam("ownerName") String ownerUsername,
            @RequestParam(name = "desc", defaultValue = "NULL") String description
    ) {
        playlistService.updatePlaylistDescription(ownerUsername, playlistName, description);

        Playlist playlist = playlistService.getPlaylist(ownerUsername, playlistName);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @PutMapping("/update-visibility")
    public ResponseEntity updatePlaylistVisibility(
            @RequestParam(name = "name", defaultValue = "NULL") String playlistName,
            @RequestParam("ownerName") String ownerUsername,
            @RequestParam(name = "visibility", defaultValue = "NULL") String visibility
    ) {
        String username = ""; // TODO: Get Username
        playlistService.updatePlaylistVisibility(ownerUsername, playlistName, visibility);

        Playlist playlist = playlistService.getPlaylist(ownerUsername, playlistName);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @DeleteMapping("/{playlist}")
    public ResponseEntity deletePlaylist(@PathVariable("playlist") String playlistName, @RequestParam("ownerName") String ownerUsername) {
        playlistService.deletePlaylist(ownerUsername, playlistName);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/get-participants/{playlist}")
    public ResponseEntity getParticipants(@PathVariable("playlist") String playlistName, @RequestParam("ownerName") String ownerUsername) {
        return new ResponseEntity<>(playlistService.getParticipants( ownerUsername, playlistName), HttpStatus.OK);
    }

    @GetMapping("/contains-track")
    public ResponseEntity containsTrack(
            @RequestParam("playlist") String playlistName,
            @RequestParam("ownerUsername") String ownerUsername,
            @RequestParam("trackName") String trackName
    ){
        boolean doesContain = playlistService.containsTrack(ownerUsername, playlistName, trackName);
        return new ResponseEntity<>(doesContain, HttpStatus.OK);
    }

    @GetMapping("/likes/{playlist}")
    public ResponseEntity getLikes(@PathVariable("playlist") String playlistName, @RequestParam("ownerName") String ownerUsername) {
        List<String> likes = playlistService.getLikes(ownerUsername, playlistName);

        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @GetMapping("/like-count/{playlist}")
    public ResponseEntity getLikeCount(@PathVariable("playlist") String playlistName, @RequestParam("ownerName") String ownerUsername) {
        List<String> likes = playlistService.getLikes(ownerUsername, playlistName);

        return new ResponseEntity<>(likes.size(), HttpStatus.OK);
    }

    @PostMapping("/toggle-like/")
    public void likes(@RequestParam("user") String ownerUsername, @RequestParam("playlist") String playlistName) {
        String likedByUsername = ""; // TODO: Get Username
        playlistService.toggleLike(ownerUsername, likedByUsername, playlistName);
    }

    @GetMapping("/is-liked-by/")
    public ResponseEntity isLikedBy(@RequestParam("user") String username, @RequestParam("playlist") String playlistName, @RequestParam("ownerName") String ownerUsername) {
        List<String> likes = playlistService.getLikes(ownerUsername, playlistName);
        boolean doesLike = likes.contains(username);

        return new ResponseEntity<>(doesLike, HttpStatus.OK);
    }

    @GetMapping("/track-added-by/{playlistName}/{trackName}/")
    public ResponseEntity trackAddedBy(
            @PathVariable("playlistName") String playlistName,
            @RequestParam("ownerName") String ownerUsername,
            @PathVariable("trackName") String trackName
    ) {
        String added_by = playlistService.trackAddedBy(ownerUsername, playlistName, trackName);

        return new ResponseEntity<>(added_by, HttpStatus.OK);
    }

    @GetMapping("/is-participant/")
    public ResponseEntity<Boolean> isParticipant(@RequestParam("user") String username, @RequestParam("playlist") String playlistName, @RequestParam("ownerName") String ownerUsername) {
        boolean isParticipant = playlistService.getParticipants(ownerUsername, playlistName).contains(username);

        return new ResponseEntity<>(isParticipant, HttpStatus.OK);
    }

    @GetMapping("/user/{user}/")
    public  ResponseEntity<List<Playlist>> getPlaylistsByUser(@RequestParam("user") String username) {
        List<Playlist> lp = playlistService.getPlaylistByUser(username);
        return new ResponseEntity<>(lp, HttpStatus.OK);
    }
}
