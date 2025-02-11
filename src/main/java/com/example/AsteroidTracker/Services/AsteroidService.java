package com.example.AsteroidTracker.Services;

import com.example.AsteroidTracker.Models.*;
import com.example.AsteroidTracker.Utils.HelperUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AsteroidService {

    @Value("${nasa.api.key}")
    private String apiKey;


    public String getApiUrl() {
        return "https://api.nasa.gov/neo/rest/v1/feed?api_key=" + apiKey;
    }

    public AsteroidData getData() {
        ObjectMapper mapper = new ObjectMapper();
        AsteroidData asteroidData;
        try {
            asteroidData = mapper.readValue(new URL(getApiUrl()), AsteroidData.class); // This reads a JSON file and converts it into an AsteroidData object.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return asteroidData;
    }

    public NearEarthObjects getAsteroidByID(String id) {
        NearEarthObjects asteroidObject = new NearEarthObjects();
        for (List<NearEarthObjects> listOfAsteroids : getData().getNearEarthObjects().values()) {
            for (NearEarthObjects asteroid : listOfAsteroids) {
                if (HelperUtilities.isNotNull(id)) {
                    if (asteroid.getId().equals(id)) {
                        asteroidObject = asteroid;
                        return asteroidObject;
                    }
                }
            }
        }
        return asteroidObject;
    }

    public List<NearEarthObjects> getAsteroidsByTodayDate() {
        List<NearEarthObjects> todayAsteroids = new ArrayList<>();
        LocalDate todayDate = java.time.LocalDate.now();
        for (List<NearEarthObjects> listOfAsteroids : getData().getNearEarthObjects().values()) {
            for (NearEarthObjects asteroid : listOfAsteroids) {
                for (CloseApproachData approachData : asteroid.getCloseApproachData()) {
                    if (approachData.getCloseApproachDate().equals(todayDate)) {
                        todayAsteroids.add(asteroid);
                    }
                }
            }
        }
        return todayAsteroids;
    }

    public List<NearEarthObjects> getAsteroidsWithinDateRange(LocalDate startDate, LocalDate endDate) {
        List<NearEarthObjects> asteroidsWithinRange = new ArrayList<>();
        for (List<NearEarthObjects> listOfAsteroids : getData().getNearEarthObjects().values()) {
            for (NearEarthObjects asteroid : listOfAsteroids) {
                for (CloseApproachData approachData : asteroid.getCloseApproachData()) {
                    if (approachData.getCloseApproachDate().isAfter(startDate) && approachData.getCloseApproachDate().isBefore(endDate)) {
                        asteroidsWithinRange.add(asteroid);
                    }
                }
            }
        }
        return asteroidsWithinRange;
    }

    public List<NearEarthObjects> getAsteroidsLargerThan(Double size) {
        List<NearEarthObjects> asteroidsLargerThan = new ArrayList<>();
        for (List<NearEarthObjects> listOfAsteroids : getData().getNearEarthObjects().values()) {
            for (NearEarthObjects asteroid : listOfAsteroids) {
                EstimatedDiameter diameter = asteroid.getEstimatedDiameter();
                Measurement measurement = diameter.getKilometers();
                Double avgSize = (measurement.getEstimatedDiameterMin() + measurement.getEstimatedDiameterMax()) / 2;
                if (HelperUtilities.isNotNull(size) && avgSize > size) {
                    asteroidsLargerThan.add(asteroid);
                }
            }
        }
        return asteroidsLargerThan;
    }

    public List<NearEarthObjects> getPotentiallyHazardousAsteroids() {
        List<NearEarthObjects> potentiallyHazardousAsteroids = new ArrayList<>();
        for (List<NearEarthObjects> asteroidList : getData().getNearEarthObjects().values()) {
            for (NearEarthObjects asteroid : asteroidList) {
                if (asteroid.getPotentiallyHazardousAsteroid()) {
                    potentiallyHazardousAsteroids.add(asteroid);
                }
            }
        }
        return potentiallyHazardousAsteroids;
    }

    public List<NearEarthObjects> getClosestAsteroidsByDistance(Integer topN) {
        List<NearEarthObjects> closestAsteroids = new ArrayList<>();
        // Collect all asteroids from different dates
        for (List<NearEarthObjects> asteroidList : getData().getNearEarthObjects().values()) {
            closestAsteroids.addAll(asteroidList);
        }
        // Sort by closest distance (ascending)
        closestAsteroids.sort(Comparator.comparingDouble(this::getMissDistance)); //Each individual asteroid will be called each time
        // Return the top N closest asteroids
        return closestAsteroids.subList(0, Math.min(topN, closestAsteroids.size()));  //Math.min so in case of user input a number too high
        // no indexOutOfBoundError will be faced
    }


    // Helper method to extract the miss distance in kilometers
    public Double getMissDistance(NearEarthObjects asteroid) {
        if (!asteroid.getCloseApproachData().isEmpty()) {
            for (CloseApproachData approachData : asteroid.getCloseApproachData()) {
                return approachData.getMissDistance().getKilometers();
            }
        }
        return Double.MAX_VALUE; // Assign max value if no distance available
    }

    public List<NearEarthObjects> getFastestAsteroids(Integer topN) {
        List<NearEarthObjects> fastestAsteroids = new ArrayList<>();
        for (List<NearEarthObjects> asteroidList : getData().getNearEarthObjects().values()) {
            fastestAsteroids.addAll(asteroidList);
        }
        fastestAsteroids.sort(Comparator.comparingDouble(this::getSpeed).reversed());
        return fastestAsteroids.subList(0, Math.min(topN, fastestAsteroids.size()));
    }

    public Double getSpeed(NearEarthObjects asteroid) {
        if (!asteroid.getCloseApproachData().isEmpty()) {
            for (CloseApproachData approachData : asteroid.getCloseApproachData()) {
                return approachData.getRelativeVelocity().getKilometersPerHour();
            }
        }
        return Double.MAX_VALUE;
    }

    public List<NearEarthObjects> getLargestAsteroids(Integer topN) {
        List<NearEarthObjects> largestAsteroids = new ArrayList<>();
        for (List<NearEarthObjects> listOfAsteroids : getData().getNearEarthObjects().values()) {
            largestAsteroids.addAll(listOfAsteroids);
        }
        largestAsteroids.sort(Comparator.comparingDouble(this::getSize).reversed());
        return largestAsteroids.subList(0, Math.min(topN, largestAsteroids.size()));
    }

    public Double getSize(NearEarthObjects asteroid) {
        if (HelperUtilities.isNotNull(asteroid.getEstimatedDiameter())) {
            for (List<NearEarthObjects> listOfAsteroids : getData().getNearEarthObjects().values()) {
                for (NearEarthObjects object : listOfAsteroids) {
                    EstimatedDiameter diameter = object.getEstimatedDiameter();
                    Measurement measurement = diameter.getKilometers();
                    return (measurement.getEstimatedDiameterMin() + measurement.getEstimatedDiameterMax()) / 2;
                }
            }
        }
        return Double.MAX_VALUE;
    }

}