package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.services.ArtistPhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class ArtistPhotosController {
    @Autowired
    private final ArtistPhotosService artistPhotosService;

    public ArtistPhotosController(ArtistPhotosService artistPhotosService) {
        this.artistPhotosService = artistPhotosService;
    }

    @GetMapping(value = "/artist-images/{artist}")
    public ResponseEntity getImageDynamicType(
            @PathVariable("artist") String artist
    ) {
       return new ResponseEntity<>(artistPhotosService.imageUrls(artist), HttpStatus.OK);
    }
}
