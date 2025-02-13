package com.example.AsteroidTracker;

import com.example.AsteroidTracker.Controllers.AsteroidController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AsteroidTrackerApplication {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(AsteroidTrackerApplication.class, args);
	}

}
