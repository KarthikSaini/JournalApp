package com.journalApp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.journalApp.entity.JournalEntry;
import com.journalApp.entity.User;
import com.journalApp.service.JournalEntryService;
import com.journalApp.service.UserService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
	
	@Autowired
	private JournalEntryService journalEntryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private org.springframework.data.mongodb.core.MongoTemplate mongoTemplate;
	
	@GetMapping("/db-check")
	public String dbCheck() {
	    return mongoTemplate.getDb().getName();
	}
	
	
	@GetMapping("{username}")
	public ResponseEntity<?> getAll(@PathVariable String username){
		User user = userService.findById(username);
		List<JournalEntry> allEntry = user.getJournalEntries();
		if(allEntry != null && !allEntry.isEmpty()) {
			return new ResponseEntity<>(allEntry,HttpStatus.OK);
		} 
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {
		try {
			myEntry.setDate(LocalDateTime.now());
			journalEntryService.saveEntry(myEntry);
			return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/id/{myId}")
	public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
		 
		Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
		if(journalEntry.isPresent()) {
			return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/id/{myId}")
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
		journalEntryService.deleteById(myId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/id/{myId}")
	public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
		try {
			JournalEntry oldEntry = journalEntryService.findById(myId).orElse(null);
			if(oldEntry != null) {
				oldEntry.setTittle(newEntry.getTittle() != null && !newEntry.getTittle().equals("") ? newEntry.getTittle() : oldEntry.getTittle());
				oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
				journalEntryService.saveEntry(oldEntry);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
				
			return new ResponseEntity<>(newEntry, HttpStatus.BAD_REQUEST);
		}
	}
}
