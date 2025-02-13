package com.example.AsteroidTracker.Services;

import com.example.AsteroidTracker.DTOs.*;
import com.example.AsteroidTracker.Models.*;
import com.example.AsteroidTracker.Utils.HelperUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class AsteroidService {

    @Value("${nasa.api.key}")
    private String apiKey;


    public String getApiUrl() {
        return "https://api.nasa.gov/neo/rest/v1/feed?api_key=" + apiKey;
    }

    public AsteroidDataDTO getData() {
        ObjectMapper mapper = new ObjectMapper();
        AsteroidData asteroidData;
        try {
            asteroidData = mapper.readValue(new URL(getApiUrl()), AsteroidData.class); // This reads a JSON file and converts it into an AsteroidData object.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return AsteroidDataDTO.convertToDTO(asteroidData);
    }

    public NearEarthObjectsDTO getAsteroidByID(Integer id) {
        NearEarthObjectsDTO asteroidObject = new NearEarthObjectsDTO();
        for (List<NearEarthObjectsDTO> listOfAsteroids : getData().getNearEarthObjects().values()) {
            for (NearEarthObjectsDTO asteroid : listOfAsteroids) {
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

    public List<NearEarthObjectsDTO> getAsteroidsByTodayDate() {
        List<NearEarthObjectsDTO> todayAsteroids = new ArrayList<>();
        LocalDate todayDate = java.time.LocalDate.now();
        for (List<NearEarthObjectsDTO> listOfAsteroids : getData().getNearEarthObjects().values()) {
            for (NearEarthObjectsDTO asteroid : listOfAsteroids) {
                for (CloseApproachDataDTO approachData : asteroid.getCloseApproachData()) {
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    df = df.withLocale(Locale.ENGLISH);
                    LocalDate closeApproachDate = LocalDate.parse(approachData.getCloseApproachDate(), df);
                    if (closeApproachDate.equals(todayDate)) {
                        todayAsteroids.add(asteroid);
                    }
                }
            }
        }
        return todayAsteroids;
    }

    public List<NearEarthObjectsDTO> getAsteroidsWithinDateRange(LocalDate startDate, LocalDate endDate) {
        List<NearEarthObjectsDTO> asteroidsWithinRange = new ArrayList<>();
        for (List<NearEarthObjectsDTO> listOfAsteroids : getData().getNearEarthObjects().values()) {
            for (NearEarthObjectsDTO asteroid : listOfAsteroids) {
                for (CloseApproachDataDTO approachData : asteroid.getCloseApproachData()) {
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    df = df.withLocale(Locale.ENGLISH);
                    LocalDate closeApproachDate = LocalDate.parse(approachData.getCloseApproachDate(), df);
                    if (closeApproachDate.isAfter(startDate) && closeApproachDate.isBefore(endDate)) {
                        asteroidsWithinRange.add(asteroid);
                    }
                }
            }
        }
        return asteroidsWithinRange;
    }

    public List<NearEarthObjectsDTO> getAsteroidsLargerThan(Double size) {
        List<NearEarthObjectsDTO> asteroidsLargerThan = new ArrayList<>();
        for (List<NearEarthObjectsDTO> listOfAsteroids : getData().getNearEarthObjects().values()) {
            for (NearEarthObjectsDTO asteroid : listOfAsteroids) {
                EstimatedDiameterDTO diameter = asteroid.getEstimatedDiameter();
                MeasurementDTO measurement = diameter.getKilometers();
                Double avgSize = (measurement.getEstimatedDiameterMin() + measurement.getEstimatedDiameterMax()) / 2;
                if (HelperUtilities.isNotNull(size) && avgSize > size) {
                    asteroidsLargerThan.add(asteroid);
                }
            }
        }
        return asteroidsLargerThan;
    }

    public List<NearEarthObjectsDTO> getPotentiallyHazardousAsteroids() {
        List<NearEarthObjectsDTO> potentiallyHazardousAsteroids = new ArrayList<>();
        for (List<NearEarthObjectsDTO> asteroidList : getData().getNearEarthObjects().values()) {
            for (NearEarthObjectsDTO asteroid : asteroidList) {
                if (asteroid.isPotentiallyHazardousAsteroid()) {
                    potentiallyHazardousAsteroids.add(asteroid);
                }
            }
        }
        return potentiallyHazardousAsteroids;
    }

    public List<NearEarthObjectsDTO> getClosestAsteroidsByDistance(Integer topN) {
        List<NearEarthObjectsDTO> closestAsteroids = new ArrayList<>();
        // Collect all asteroids from different dates
        for (List<NearEarthObjectsDTO> asteroidList : getData().getNearEarthObjects().values()) {
            closestAsteroids.addAll(asteroidList);
        }
        closestAsteroids.sort(Comparator.comparingDouble(closestAsteriod -> closestAsteriod.getCloseApproachData().get(0).getMissDistance().getKilometers()));
        // Return the top N closest asteroids
        return closestAsteroids.subList(0, Math.min(topN, closestAsteroids.size()));  //Math.min so in case of user input a number too high
        // no indexOutOfBoundError will be faced
    }


   /* // Helper method to extract the miss distance in kilometers
    public Double getMissDistance(NearEarthObjects asteroid) {
        if (!asteroid.getCloseApproachData().isEmpty()) {
            for (CloseApproachData approachData : asteroid.getCloseApproachData()) {
                return approachData.getMissDistance().getKilometers();
            }
        }
        return Double.MAX_VALUE; // Assign max value if no distance available
    }*/

    public List<NearEarthObjectsDTO> getFastestAsteroids(Integer topN) {
        List<NearEarthObjectsDTO> fastestAsteroids = new ArrayList<>();
        for (List<NearEarthObjectsDTO> asteroidList : getData().getNearEarthObjects().values()) {
            fastestAsteroids.addAll(asteroidList);
        }
        fastestAsteroids.sort(Comparator.comparingDouble(fastestAsteroid -> fastestAsteroid.getCloseApproachData().get(0).getRelativeVelocity().getKilometersPerHour()));
        return fastestAsteroids.subList(0, Math.min(topN, fastestAsteroids.size())).reversed();
    }

    /*public Double getSpeed(NearEarthObjects asteroid) {
        if (!asteroid.getCloseApproachData().isEmpty()) {
            for (CloseApproachData approachData : asteroid.getCloseApproachData()) {
                return approachData.getRelativeVelocity().getKilometersPerHour();
            }
        }
        return Double.MAX_VALUE;
    }*/

    public List<NearEarthObjectsDTO> getLargestAsteroids(Integer topN) {
        List<NearEarthObjectsDTO> largestAsteroids = new ArrayList<>();
        for (List<NearEarthObjectsDTO> listOfAsteroids : getData().getNearEarthObjects().values()) {
            largestAsteroids.addAll(listOfAsteroids);
        }
        largestAsteroids.sort(Comparator.comparingDouble(largestAsteroid ->
                (largestAsteroid.getEstimatedDiameter().getKilometers().getEstimatedDiameterMin() +
                        largestAsteroid.getEstimatedDiameter().getKilometers().getEstimatedDiameterMax()) / 2));

        return largestAsteroids.subList(0, Math.min(topN, largestAsteroids.size())).reversed();
    }

    /*public Double getSize(NearEarthObjects asteroid) {
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
    }*/
}