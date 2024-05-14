package com.apica.JournalingService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apica.JournalingService.dao.JournalRepository;
import com.apica.JournalingService.model.JournalEntry;

@RestController
@RequestMapping("/users")
public class JournalController {

    @Autowired
    private JournalRepository journalRepository; 

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable String id) {
        return ResponseEntity.ok(journalRepository.findById(id).orElseThrow());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> updateJournal(@PathVariable String id, @RequestBody JournalEntry journalEntry) {
        ResponseEntity<JournalEntry> toRet = journalRepository.findById(id).isPresent() ?
        ResponseEntity.ok(journalRepository.save(journalEntry)) :
        ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        return toRet;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJournal(@PathVariable String id) {
        journalRepository.deleteById(id);
        return ResponseEntity.ok("SUCCESS");
    }

    @GetMapping("/")
    public ResponseEntity<List<JournalEntry>> getAllJournals() {
        List<JournalEntry> journalEntries = new ArrayList<>();
        journalRepository.findAll().forEach(journalEntries::add);
        return ResponseEntity.ok(journalEntries);
    }

    @PostMapping("/signup")
    public ResponseEntity<JournalEntry> createJournal(@RequestBody JournalEntry journalEntry) {
        return ResponseEntity.ok(journalRepository.save(journalEntry));
    }
    
    
}
