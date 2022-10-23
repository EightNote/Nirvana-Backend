package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.services.AlbumService;
import com.eightnote.nirvana.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class AlbumController {
    @Autowired
    private final AlbumService albumService;
    @Autowired
    private final GenreService genreService;

    public AlbumController(AlbumService albumService, GenreService genreService) {
        this.albumService = albumService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public ResponseEntity getAlbum(@RequestParam("albumName") String albumName){
        return  new ResponseEntity<>(albumService.getAlbum(albumName), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity createAlbum(
            @RequestParam("title") String albumName,
            @RequestParam("logo") String albumLogo,
            @RequestParam("artistId") String artistId,
            @RequestParam("genre") String genre
    ) {
        int genre_id = genreService.getGenreID(genre);
        albumService.createAlbum(albumName, albumLogo, artistId, genre_id);
        Album album = albumService.getAlbum(albumName);
        return new ResponseEntity<>(album, HttpStatus.OK);
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
            @PathVariable("album") String album,
            @RequestParam("username") String username
    ) {
        return new ResponseEntity<>(albumService.isLikedBy(username, album), HttpStatus.OK);
    }

    @PostMapping("/toggle-like/")
    public void toggleLike(
            @RequestParam("username") String username,
            @RequestParam("username") String albumName
    ) {
        albumService.toggleLike(username, albumName);
    }

    @GetMapping("/album/is-released-in-country/{album}")
    public ResponseEntity isReleasedInCountry(
            @PathVariable("album") String album,
            @RequestParam("album") String countryName
    ) {
        return new ResponseEntity<>(albumService.isReleasedInCountry(album, countryName), HttpStatus.OK);
    }
}
