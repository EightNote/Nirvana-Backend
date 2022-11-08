package com.eightnote.nirvana.controllers;


import com.eightnote.nirvana.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
@CrossOrigin
@RequestMapping("genre/")
public class GenreController {
    @Autowired
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }


    @GetMapping("/get-tracks/{genre}")
    public ResponseEntity getGenre(@PathVariable("genre") String genre) {
        return new ResponseEntity<>(genreService.getTracks(genre), HttpStatus.OK);
    }

    @PostMapping("{genreName}")
    public ResponseEntity createGenre(@PathVariable("genreName") String genreName) {
        genreService.createGenre(genreName);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }


    @GetMapping("/{genreName}")
    public ResponseEntity getGenreID(@PathVariable("genreName") String genreName) {
        return new ResponseEntity<>(genreService.getGenre(genreName).getId(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity allGenres() {
        return new ResponseEntity<>(genreService.allGenres(), HttpStatus.OK);
    }
}
