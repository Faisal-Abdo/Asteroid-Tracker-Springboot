package com.example.AsteroidTracker.Controllers;

import com.example.AsteroidTracker.DTOs.AsteroidDataDTO;
import com.example.AsteroidTracker.DTOs.NearEarthObjectsDTO;
import com.example.AsteroidTracker.Services.AsteroidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "Asteroids")
public class AsteroidController {

    private final Logger logger = LoggerFactory.getLogger(AsteroidController.class);

    @Autowired
    private AsteroidService asteroidService;

    @GetMapping(value = "getAllAsteroidData")
    public AsteroidDataDTO convertJSONToObject() {
        return asteroidService.getData();
    }

    @GetMapping(value = "getAsteroidByID")
    public List<NearEarthObjectsDTO> getAsteroidByID(@RequestParam Integer id) {
        return Arrays.asList(asteroidService.getAsteroidByID(id));
    }

    @GetMapping(value = "getAsteroidsByTodayDate")
    public List<NearEarthObjectsDTO> getAsteroidsByTodayDate() {
        return asteroidService.getAsteroidsByTodayDate();
    }

    @GetMapping(value = "getAsteroidsWithinDateRange")
    public List<NearEarthObjectsDTO> getAsteroidsWithinDateRange(@RequestParam LocalDate startDate,
                                                                 @RequestParam LocalDate endDate) {
        return asteroidService.getAsteroidsWithinDateRange(startDate, endDate);
    }

    @GetMapping(value = "getAsteroidsLargerThan")
    public List<NearEarthObjectsDTO> getAsteroidsLargerThan(@RequestParam Double size) {
        return asteroidService.getAsteroidsLargerThan(size);
    }

    @GetMapping(value = "getPotentiallyHazardousAsteroids")
    public List<NearEarthObjectsDTO> getPotentiallyHazardousAsteroids() {
        try {
            List<NearEarthObjectsDTO> result = asteroidService.getPotentiallyHazardousAsteroids();
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    @GetMapping(value = "getClosestAsteroidsByDistance")
    public List<NearEarthObjectsDTO> getClosestAsteroidsByDistance(@RequestParam Integer topN) {
        return asteroidService.getClosestAsteroidsByDistance(topN);
    }

    @GetMapping(value = "getFastestAsteroids")
    public List<NearEarthObjectsDTO> getFastestAsteroids(@RequestParam Integer topN) {
        return asteroidService.getFastestAsteroids(topN);
    }

    @GetMapping(value = "getLargestAsteroids")
    public List<NearEarthObjectsDTO> getLargestAsteroids(@RequestParam Integer topN) {
        return asteroidService.getLargestAsteroids(topN);
    }
}