package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.services.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Component
public class FollowerController {
    JdbcUserDetailsManager userDetailsManager;
    @Autowired
    private final FollowerService followerService;


    private final GrantedAuthority authority_artist = () -> "artist";
    private final GrantedAuthority authority_user = () -> "user";

    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }


    @PostMapping("/follow")
    public ResponseEntity<?> follow(
            @RequestParam("user1") String user1,
            @RequestParam("user2") String user2
    ) {
        var u1 = userDetailsManager.loadUserByUsername(user1);
        var u2 = userDetailsManager.loadUserByUsername(user2);

        if (u1 != null && u2 != null && u1.getAuthorities().contains(authority_user) && u2.getAuthorities().contains(authority_artist)) {
            followerService.addFollower(u1, u2);

            return new ResponseEntity<>("%s follows %s".formatted(user1, user2), HttpStatus.OK);
        }

        List<String> response = new ArrayList<>();
        if (u1 == null || !u1.getAuthorities().contains(authority_user)) {
            response.add("%s is not a user".formatted(user1));
        }
        if (u2 == null || !u2.getAuthorities().contains(authority_artist)) {
            response.add("%s is not an artist".formatted(user2));
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/unfollow")
    public ResponseEntity unfollow(
            @RequestParam("user1") String user1,
            @RequestParam("user2") String user2
    ) {
        var u1 = userDetailsManager.loadUserByUsername(user1);
        var u2 = userDetailsManager.loadUserByUsername(user2);

        if (u1 != null && u2 != null && u1.getAuthorities().contains(authority_user) && u2.getAuthorities().contains(authority_artist)) {
            followerService.removeFollower(u1, u2);

            return new ResponseEntity<>("%s does not follow %s".formatted(user1, user2), HttpStatus.OK);
        }

        List<String> response = new ArrayList<>();
        if (u1 == null || !u1.getAuthorities().contains(authority_user)) {
            response.add("%s is not a user".formatted(user1));
        }
        if (u2 == null || !u2.getAuthorities().contains(authority_artist)) {
            response.add("%s is not an artist".formatted(user2));
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("followers/{user}")
    public ResponseEntity followers(
            @PathVariable("user") String username
    ) {
        if (userDetailsManager.loadUserByUsername(username) == null) {
            return new ResponseEntity<>(username + " does not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(followerService.getFollowers(username), HttpStatus.OK);
    }

    @GetMapping("followers/{artist}/is_followed_by/{user}")
    public ResponseEntity<Boolean> isFollowedBy(
            @PathVariable("artist") String artist, @PathVariable("user") String username
    ) {
        return new ResponseEntity<>(followerService.isFollowedBy(artist, username), HttpStatus.OK);
    }
}
