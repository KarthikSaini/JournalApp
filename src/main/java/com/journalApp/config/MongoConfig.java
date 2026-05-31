package com.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {

//        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    	MongoClient mongoClient = MongoClients.create("mongodb+srv://karthiksaini7800_db_user:ZhJTuEvZFrgAxBAr@cluster0.qxhux60.mongodb.net/?appName=Cluster0");

        return new MongoTemplate(mongoClient, "journaldb");
    }
}