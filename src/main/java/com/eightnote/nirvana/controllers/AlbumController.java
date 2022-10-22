package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class AlbumController {
    @Autowired
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/album/{album}")
    public ResponseEntity album(
            @PathVariable("album") String album
    ) {
        return new ResponseEntity<>(albumService.getAlbum(album), HttpStatus.OK);
    }

    @GetMapping("/album/get-like-count/{album}")
    public ResponseEntity getLikeCount(
            @PathVariable("album") String album
    ) {
        return new ResponseEntity<>(albumService.getLikeCount(album), HttpStatus.OK);
    }

    @GetMapping("/album/get-likes/{album}")
    public ResponseEntity getLikes(
            @PathVariable("album") String album
    ) {
        return new ResponseEntity<>(albumService.getLikes(album), HttpStatus.OK);
    }

    @GetMapping("/album/is-liked-by/{album}")
    public ResponseEntity isLikedBy(
            @PathVariable("album") String album
    ) {
        return new ResponseEntity<>(albumService.isLikedBy(album), HttpStatus.OK);
    }

    @GetMapping("/album/is-released-in-country/{album}")
    public ResponseEntity isReleasedInCountry(
            @PathVariable("album") String album
    ) {
        return new ResponseEntity<>(albumService.isReleasedInCountry(album), HttpStatus.OK);
    }
}
