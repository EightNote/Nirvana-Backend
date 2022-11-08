package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.login.JSONWebTokenHandler;
import com.eightnote.nirvana.login.NirvanaAuthenticationManager;
import com.eightnote.nirvana.models.ArtistAccountDetails;
import com.eightnote.nirvana.models.NirvanaUser;
import com.eightnote.nirvana.models.UserAccountDetails;
import com.eightnote.nirvana.services.NirvanaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Component
@RestController
@CrossOrigin
@RequestMapping("user/")
public class NirvanaUserController {
    @Autowired
    private NirvanaAuthenticationManager authenticationManager;

    @Autowired
    private JSONWebTokenHandler jsonWebTokenHandler;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private final NirvanaUserService nirvanaUserService;

    public NirvanaUserController(NirvanaUserService nirvanaUserService) {
        this.nirvanaUserService = nirvanaUserService;
    }

    @PostMapping("sign-up/user/")
    public ResponseEntity<?> createUser(@RequestBody UserAccountDetails user) {
        nirvanaUserService.createUser(user.encrypted());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("sign-up/artist/")
    public ResponseEntity<?> createArtistAccount(@RequestBody ArtistAccountDetails user) {
        String passwordDecoded = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(passwordDecoded));

        nirvanaUserService.createArtistAccount(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("remove-user/")
    public ResponseEntity<?> removeUser(@RequestBody NirvanaUser user) {
        nirvanaUserService.removeUser(user);
        return new ResponseEntity<>("Removed user %s".formatted(user.getUsername()), HttpStatus.OK);
    }

    @GetMapping("get-artist-detail/{username}")
    public ResponseEntity<?> getArtistDetail(@PathVariable("username") String userName) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = nirvanaUserService.loadUserByUsername(userName);
        if (user == null) {
            return new ResponseEntity<>("User %s does not exist".formatted(userName), HttpStatus.BAD_REQUEST);
        }
        nirvanaUserService.getArtistDetail(user);
        return new ResponseEntity<>("Details of Artist %s".formatted(userName), HttpStatus.OK);
    }
    @GetMapping("/getAllArtist/")
    public ResponseEntity<?> getAllArtist(){
        nirvanaUserService.getAllArtist();
        return new ResponseEntity<>(nirvanaUserService.getAllArtist(),HttpStatus.OK);
    }

    @PostMapping("sign-in/")
    public ResponseEntity<?> signIn(@RequestBody NirvanaUser user) throws Exception {
        var authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        try {
            var authentication2 = authenticationManager.authenticate(authentication);
            System.out.println(authentication2.getName());
        } catch (DisabledException e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>("USER DISABLED", HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.OK);
        }



        final String token = jsonWebTokenHandler.generateToken(user);
        var response = new HashMap<String, String>();

        user = nirvanaUserService.loadUserByUsername(user.getUsername());

        response.put("token", token);
        response.put("username", jsonWebTokenHandler.getUsernameFromToken(token));
        response.put("role", user.getRole());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

