package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.models.NirvanaUser;
import com.eightnote.nirvana.services.FollowerService;
import com.eightnote.nirvana.services.NirvanaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Component
public class FollowerController {
    @Autowired
    private final NirvanaUserService nirvanaUserService;
    @Autowired
    private final FollowerService followerService;

    public FollowerController(NirvanaUserService nirvanaUserService, FollowerService followerService) {
        this.nirvanaUserService = nirvanaUserService;
        this.followerService = followerService;
    }

    @PostMapping("/follow")
    public ResponseEntity<?> follow(@RequestParam("followed_artist") String followed_artist) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String follower_username = auth.getName();
        System.out.println(auth);

        var userInstance = nirvanaUserService.loadUserByUsername(follower_username);

        var artistInstance = nirvanaUserService.loadUserByUsername(followed_artist);

        if (artistInstance.getUsername().equals("anonymousUser")) {
            return new ResponseEntity<>("No such artist", HttpStatus.BAD_REQUEST);
        }

        if (userInstance.getRole().equals("artist")) {
            return new ResponseEntity<>("User logged-in is an artist, and not a user", HttpStatus.BAD_REQUEST);
        }

        followerService.addFollower(artistInstance, userInstance);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollow(@RequestParam("followed_artist") String followed_artist) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String follower_username = auth.getName();
        System.out.println(followed_artist);

        var userInstance = nirvanaUserService.loadUserByUsername(follower_username);

        var artistInstance = nirvanaUserService.loadUserByUsername(followed_artist);

        if (artistInstance.getUsername().equals("anonymousUser")) {
            return new ResponseEntity<>("No such artist", HttpStatus.BAD_REQUEST);
        }

        if (userInstance.getRole().equals("artist")) {
            return new ResponseEntity<>("User logged-in is an artist, and not a user", HttpStatus.BAD_REQUEST);
        }

        followerService.removeFollower(artistInstance, userInstance);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }


    @GetMapping("followers/{user}")
    public ResponseEntity<?> followers(@PathVariable("user") String username) {
        NirvanaUser userInstance;
        userInstance = nirvanaUserService.loadUserByUsername(username);

        if (userInstance == null) {
            return new ResponseEntity<>("No such artist", HttpStatus.BAD_REQUEST);
        }

        if (userInstance.getRole().equals("user")) {
            return new ResponseEntity<>(userInstance.getUsername() + " is not an artist, but a user", HttpStatus.BAD_REQUEST);
        }

        var l  = followerService.getFollowers(username);

        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("followers/{artist}/is_followed_by/{user}")
    public ResponseEntity<?> isFollowedBy(
            @PathVariable("artist") String artist, @PathVariable("user") String username
    ) {
        return new ResponseEntity<>(followerService.isFollowedBy(artist, username), HttpStatus.OK);
    }
}
