package com.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.journalApp.entity.User;

@Service
public interface UserRepository extends MongoRepository<User, ObjectId> {
	
	User findByUsername(String username);

}
