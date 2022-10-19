package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.services.RecordLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@RequestMapping("/recordlabel")
public class RecordLabelController {
    @Autowired
    private final RecordLabelService recordLabelService;

    public RecordLabelController(RecordLabelService recordLabelService) {this.recordLabelService = recordLabelService;}

    @GetMapping("/id/{id}")
    public ResponseEntity getRecordLabel(@RequestParam("id") Integer recordLabelId) {
        return new ResponseEntity<>(recordLabelService.getRecordLabel(recordLabelId), HttpStatus.OK);
    }
}
