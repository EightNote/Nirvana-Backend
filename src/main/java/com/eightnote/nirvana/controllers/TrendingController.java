package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.services.TrendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@CrossOrigin
@RequestMapping("trending/")
public class TrendingController {
    @Autowired
    private final TrendingService trendingService;

    public TrendingController(TrendingService trendingService) {
        this.trendingService = trendingService;
    }

    @GetMapping("")
    public ResponseEntity<?> getTrending() {
        var list = trendingService.getTrending();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
