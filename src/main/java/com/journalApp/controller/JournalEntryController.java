package com.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.journalApp.entity.JournalEntry;

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
public class JournalEntryController {
	
	private Map<Long, JournalEntry> journalEntry = new HashMap<>();
	
	@GetMapping
	public List<JournalEntry> getAll(){
		return new ArrayList<>(journalEntry.values());
	}
	
	@GetMapping("/getAll")
	public List<JournalEntry> getAllEntries(){
		return new ArrayList<>(journalEntry.values());
	}
	
	@PostMapping
	public boolean createEntry(@RequestBody JournalEntry myEntry) {
		journalEntry.put(myEntry.getId(), myEntry);
		return true;
	}
	
	@GetMapping("/id/{myId}")
	public JournalEntry getJournalEntryById(@PathVariable Long myId) {
		return journalEntry.get(myId);
	}
	
	@DeleteMapping("/id/{myId}")
	public JournalEntry deleteJournalEntryById(@PathVariable Long myId) {
		return journalEntry.remove(myId);
	}
	
	@PutMapping("/id/{myId}")
	public JournalEntry updateJournalEntryById(@PathVariable Long myId, @RequestBody JournalEntry myEntry) {
		return journalEntry.put(myId,myEntry);
	}
}
