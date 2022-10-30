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
//        System.out.println("Creating user");
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
        var encryptedUser = new NirvanaUser(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()));
        nirvanaUserService.createUser(encryptedUser);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @GetMapping("sign-up/")
//    public ResponseEntity<?> createUserGET() {
//        System.out.println("Creating user via GET");
////        System.out.println(user.getUsername());
////        System.out.println(user.getPassword());
////        nirvanaUserService.createUser(user);
////        return "Create User GET";
//        return new ResponseEntity<>("Created", HttpStatus.OK);
//    }


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

        return new ResponseEntity<>(token.toString(), HttpStatus.OK);
    }
}

