//package com.journalApp;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.env.Environment;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.MongoTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import jakarta.annotation.PostConstruct;
//
//@EnableTransactionManagement
//@SpringBootApplication
//public class JournalAppApplication {
//	
//	@Autowired
//    private Environment env;
//
//	public static void main(String[] args) {
//		SpringApplication.run(JournalAppApplication.class, args);
//	}
//	
//	@Bean
//	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
//		return new MongoTransactionManager(dbFactory);
//	}
//	
//	@PostConstruct
//    public void printMongo() {
//        System.out.println("URI = " + env.getProperty("spring.data.mongodb.uri"));
//        System.out.println("HOST = " + env.getProperty("spring.data.mongodb.host"));
//        System.out.println("PORT = " + env.getProperty("spring.data.mongodb.port"));
//        System.out.println("DATABASE = " + env.getProperty("spring.data.mongodb.database"));
//    }
//}



package com.journalApp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.annotation.PostConstruct;

@EnableTransactionManagement
@SpringBootApplication
public class JournalAppApplication {

    @Autowired
    private Environment env;

    @Value("${my.test.property:NOT_FOUND}")
    private String testProperty;
    
    @PostConstruct
	public void printDatabaseProperty() {
	    System.out.println(
	        "Database Property = " +
	        env.getProperty("spring.data.mongodb.database")
	    );
	}
    

    public static void main(String[] args) {
        SpringApplication.run(JournalAppApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @PostConstruct
    public void printMongo() {

        System.out.println("\n================ DEBUG INFO ================\n");

        System.out.println("Active Profiles = "
                + Arrays.toString(env.getActiveProfiles()));

        System.out.println("Mongo URI = "
                + env.getProperty("spring.data.mongodb.uri"));

        System.out.println("Mongo HOST = "
                + env.getProperty("spring.data.mongodb.host"));

        System.out.println("Mongo PORT = "
                + env.getProperty("spring.data.mongodb.port"));

        System.out.println("Mongo DATABASE = "
                + env.getProperty("spring.data.mongodb.database"));

        System.out.println("Test Property = "
                + testProperty);

        System.out.println("\n===========================================\n");
    }
}