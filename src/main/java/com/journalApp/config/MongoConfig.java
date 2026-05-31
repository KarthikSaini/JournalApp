package com.journalApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {
	
	@Value("${spring.data.mongodb.userName}")
	private String userName;
	
	@Value("${spring.data.mongodb.password}")
	private String password;

    @Bean
    public MongoTemplate mongoTemplate() {

    	String url = "mongodb+srv://"+userName+":"+password+"@cluster0.qxhux60.mongodb.net/?appName=Cluster0";
    	MongoClient mongoClient = MongoClients.create(url);

        return new MongoTemplate(mongoClient, "journaldb");
    }
}