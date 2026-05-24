package com.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journalApp.entity.User;
import com.journalApp.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;	
	
	public void saveEntry(User user) {
		User savedEntry = userRepository.save(user);
	}
	
	public List<User> getAllEntry() {
		List<User> savedEntry = userRepository.findAll();
		return savedEntry;
	}
	
//	public Optional<User> findById(ObjectId id) {
//		return userRepository.findById(id);
//	}
	
	public User findById(String username) {
		return userRepository.findByUsername(username);
	}
	
	public void deleteById(ObjectId id) {
		userRepository.deleteById(id);
	}
	
}
