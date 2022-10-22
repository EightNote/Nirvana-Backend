package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class AlbumController {
    @Autowired
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
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
            @RequestParam("genre") int genreId
    ){
        albumService.createAlbum(albumName, albumLogo, artistId, genreId);
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
