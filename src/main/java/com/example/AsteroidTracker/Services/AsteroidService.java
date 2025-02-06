package com.example.AsteroidTracker.Services;

import com.example.AsteroidTracker.Models.Asteroid;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsteroidService {
    private final String NASA_API_URL = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2024-02-01&end_date=2024-02-07&api_key=DEMO_KEY";

    public String fetchNeoData() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(NASA_API_URL, String.class);
    }

    /*public Asteroid getAsteroidBySize(Double asteroidSize) {

    }*/
}
