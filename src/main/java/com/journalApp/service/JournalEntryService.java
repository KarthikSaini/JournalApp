package com.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journalApp.entity.JournalEntry;
import com.journalApp.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;	
	
	public void saveEntry(JournalEntry journalEntry) {
		JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
		System.out.println(savedEntry.getContent()+"  "+savedEntry.getId());
	}
	
	public JournalEntry getAllEntry() {
		JournalEntry savedEntry = (JournalEntry) journalEntryRepository.findAll();
		return savedEntry;
	}
	
}
