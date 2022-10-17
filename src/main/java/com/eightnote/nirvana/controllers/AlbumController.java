package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
