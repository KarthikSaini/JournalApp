package com.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.journalApp.entity.JournalEntry;
import com.journalApp.service.JournalEntryService;

import org.springframework.beans.factory.annotation.Autowired;
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
	private org.springframework.data.mongodb.core.MongoTemplate mongoTemplate;
	
	@GetMapping("/db-check")
	public String dbCheck() {
	    return mongoTemplate.getDb().getName();
	}
	
	
	@GetMapping
	public List<JournalEntry> getAll(){
//		return journalEntryService.getAllEntry();
				return null;
	}
	
	@PostMapping
	public boolean createEntry(@RequestBody JournalEntry myEntry) {
		journalEntryService.saveEntry(myEntry);
		return true;
	}
	
	@GetMapping("/id/{myId}")
	public JournalEntry getJournalEntryById(@PathVariable Long myId) {
		return null;
	}
	
	@DeleteMapping("/id/{myId}")
	public JournalEntry deleteJournalEntryById(@PathVariable Long myId) {
		return null;
	}
	
	@PutMapping("/id/{myId}")
	public JournalEntry updateJournalEntryById(@PathVariable Long myId, @RequestBody JournalEntry myEntry) {
		return null;
	}
}
