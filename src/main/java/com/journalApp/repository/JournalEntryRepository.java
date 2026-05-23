package com.journalApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.journalApp.entity.JournalEntry;

@Service
public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {

}
