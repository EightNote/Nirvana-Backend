package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.login.JSONWebTokenHandler;
import com.eightnote.nirvana.login.NirvanaAuthenticationManager;
import com.eightnote.nirvana.models.NirvanaUser;
import com.eightnote.nirvana.services.NirvanaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @PostMapping("sign-up/")
    public ResponseEntity<?> createUser(@RequestBody NirvanaUser user) {
        var encryptedUser = new NirvanaUser(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()), user.getRole());
        nirvanaUserService.createUser(encryptedUser);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("remove-user/")
    public ResponseEntity<?> removeUser(@RequestBody NirvanaUser user) {
        nirvanaUserService.removeUser(user);
        return new ResponseEntity<>("Removed user %s".formatted(user.getUsername()), HttpStatus.OK);
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

