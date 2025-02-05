package com.example.AsteroidTracker.Controllers;

import com.example.AsteroidTracker.Services.AsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsteroidController {
    @Autowired
    private AsteroidService asteroidService;
}
