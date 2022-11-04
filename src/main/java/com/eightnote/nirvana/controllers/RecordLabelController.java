package com.eightnote.nirvana.controllers;

import com.eightnote.nirvana.services.RecordLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
@CrossOrigin
@RequestMapping("/recordlabel")
public class RecordLabelController {
    @Autowired
    private final RecordLabelService recordLabelService;

    public RecordLabelController(RecordLabelService recordLabelService) {this.recordLabelService = recordLabelService;}

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getRecordLabel(@RequestParam("id") Integer recordLabelId) {
        if(recordLabelId==-1){
            return new ResponseEntity<>(recordLabelService.getAllRecordLabel(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(recordLabelService.getRecordLabel(recordLabelId), HttpStatus.OK);
        }

    }
}
