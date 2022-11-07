package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.services.AlbumService;
import com.eightnote.nirvana.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@CrossOrigin
@RequestMapping("albums/")
public class AlbumController {
    @Autowired
    private final AlbumService albumService;
    @Autowired
    private final GenreService genreService;

    public AlbumController(AlbumService albumService, GenreService genreService) {
        this.albumService = albumService;
        this.genreService = genreService;
    }

    @GetMapping("all/")
    public ResponseEntity<?> getAllAlbum(){
        return  new ResponseEntity<>(albumService.getAllAlbum(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createAlbum(
            @RequestBody Album album
    ) {
        albumService.createAlbum(album.getAlbum_title(), album.getAlbum_logo(), album.getArtist_id(), album.getGenre_id());
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @GetMapping("album/{id}")
    public ResponseEntity<?>  album(
            @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(albumService.getAlbumTracks(id), HttpStatus.OK);
    }

    @GetMapping("album/get-like-count/{album}")
    public ResponseEntity<?>  getLikeCount(
            @PathVariable("album") String album
    ) {
        return new ResponseEntity<>(albumService.getLikeCount(album), HttpStatus.OK);
    }

    @GetMapping("album/get-likes/{album}")
    public ResponseEntity<?>  getLikes(
            @PathVariable("album") String album
    ) {
        return new ResponseEntity<>(albumService.getLikes(album), HttpStatus.OK);
    }

    @GetMapping("album/get-user-likes/")
    public ResponseEntity<?>  getUserLikes() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return new ResponseEntity<>(albumService.getUserLikes(username), HttpStatus.OK);
    }


    @GetMapping("album/is-liked-by/{album}")
    public ResponseEntity<?>  isLikedBy(
            @PathVariable("album") String album,
            @RequestParam("username") String username
    ) {
        return new ResponseEntity<>(albumService.isLikedBy(username, album), HttpStatus.OK);
    }

//    @PostMapping("/toggle-like/")
//    public void toggleLike(
//            @RequestParam("username") String username,
//            @RequestParam("username") String albumName
//    ) {
//        albumService.toggleLike(username, albumName);
//    }

    @GetMapping("album/is-released-in-country/{album}")
    public ResponseEntity<?>  isReleasedInCountry(
            @PathVariable("album") String album,
            @RequestParam("album") String countryName
    ) {
        return new ResponseEntity<>(albumService.isReleasedInCountry(album, countryName), HttpStatus.OK);
    }

    @GetMapping("album/artist/{artist}")
    public  ResponseEntity<?> albumsByArtist(
            @PathVariable("artist") String artist
    ) {
        return new ResponseEntity<>(albumService.getAlbumsByArtist(artist), HttpStatus.OK);
    }

    @GetMapping("likedAlbums/")
    public ResponseEntity<?> likedAlbums(

            @RequestParam("username") String username
    )
    {
        return new ResponseEntity<>(albumService.likedAlbums(username), HttpStatus.OK);
    }
}
