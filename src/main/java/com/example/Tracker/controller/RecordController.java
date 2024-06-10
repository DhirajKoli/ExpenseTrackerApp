package com.example.Tracker.controller;

import com.example.Tracker.dto.RecordDto;
import com.example.Tracker.model.Record;
import com.example.Tracker.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/apps/records")
public class RecordController {

    @Autowired
    public RecordService recordService;

    @PostMapping
    public ResponseEntity<Record> postRecord(@RequestBody RecordDto recordDto){
        return new ResponseEntity<>(recordService.postRecord(recordDto), HttpStatus.OK);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<Record> getRecord(@PathVariable("recordId") Long recordId){
        return new ResponseEntity<>(recordService.getRecord(recordId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Record>> getAllRecordsByLoggedUser(Principal principal){
        return new ResponseEntity<>(recordService.getAllRecordsByLoggedUser(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Record>> getAllRecordsByUser(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(recordService.getAllRecordsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<List<Record>> getAllRecordsByAccount(@PathVariable("accountId") Long accountId){
        return new ResponseEntity<>(recordService.getAllRecordsByAccount(accountId), HttpStatus.OK);
    }
}
