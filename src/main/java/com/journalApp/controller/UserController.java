package com.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalApp.entity.User;
import com.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getAll(){
		return userService.getAllEntry();
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		userService.saveEntry(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		User userInDb = userService.findById(user.getUsername());
		if(userInDb != null) {
			userInDb.setUsername(user.getUsername());
			userInDb.setPassword(user.getPassword());
			userService.saveEntry(userInDb);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteUser(@RequestBody User user){
		userService.deleteById(user.getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
