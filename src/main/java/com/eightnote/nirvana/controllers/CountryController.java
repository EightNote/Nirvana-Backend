package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.models.Country;
import com.eightnote.nirvana.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@CrossOrigin
@RequestMapping("/countries")
@Component
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping("{id}/")
    public ResponseEntity getCountry(
            @PathVariable(name = "id", required = true) int id
    ){System.out.println("id");return new ResponseEntity(countryService.getId(id), HttpStatus.OK);}
    @GetMapping("all/")
    public ResponseEntity getAllCountries() {
        return new ResponseEntity(countryService.getAll(), HttpStatus.OK);
    }
}