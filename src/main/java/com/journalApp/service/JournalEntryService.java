package com.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journalApp.entity.JournalEntry;
import com.journalApp.entity.User;
import com.journalApp.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;	
	
	@Autowired
	private UserService userService;
	
	public void saveEntry(JournalEntry journalEntry, String userName) {
		User user = userService.findById(userName);
		journalEntry.setDate(LocalDateTime.now());
		JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
		user.getJournalEntries().add(savedEntry);
		userService.saveEntry(user);
	}
	
	public void saveEntry(JournalEntry journalEntry) {
		journalEntryRepository.save(journalEntry);
	}
	
	public List<JournalEntry> getAllEntry() {
		List<JournalEntry> savedEntry = journalEntryRepository.findAll();
		return savedEntry;
	}
	
	public Optional<JournalEntry> findById(ObjectId id) {
		return journalEntryRepository.findById(id);
	}
	
	public void deleteById(ObjectId id, String userName) {
		User user = userService.findById(userName);
		user.getJournalEntries().removeIf(x -> x.getId().equals(id));
		userService.saveEntry(user);
		journalEntryRepository.deleteById(id);
	}
	
}
