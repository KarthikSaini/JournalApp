package com.journalApp.controller;

import java.time.LocalDateTime;
import java.util.List;
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
	
	
	@GetMapping("{userName}")
	public ResponseEntity<?> getAll(@PathVariable String userName){
		User user = userService.findById(userName);
		List<JournalEntry> allEntry = user.getJournalEntries();
		if(allEntry != null && !allEntry.isEmpty()) {
			return new ResponseEntity<>(allEntry,HttpStatus.OK);
		} 
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("{userName}")
	public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
		try {
			myEntry.setDate(LocalDateTime.now());
			journalEntryService.saveEntry(myEntry, userName);
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
	
	@DeleteMapping("/id/{userName}/{myId}")
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName) {
		journalEntryService.deleteById(myId, userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/id/{userName}/{myId}")
	public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId,
			@RequestBody JournalEntry newEntry,
			@PathVariable String userName) {
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
