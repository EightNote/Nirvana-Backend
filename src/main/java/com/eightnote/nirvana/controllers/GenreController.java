package com.eightnote.nirvana.controllers;


import com.eightnote.nirvana.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }


//    @GetMapping("/get-track-genre/{track}")
//    public ResponseEntity getGenre(@PathVariable("track") String trackName) {
//        return new ResponseEntity<>(genreService.getGenre(trackName), HttpStatus.OK);
//    }
}
