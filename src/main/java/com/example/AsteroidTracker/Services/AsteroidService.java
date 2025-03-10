package com.example.AsteroidTracker.Services;

import com.example.AsteroidTracker.DTOs.*;
import com.example.AsteroidTracker.Models.AsteroidData;
import com.example.AsteroidTracker.Models.NearEarthObjects;
import com.example.AsteroidTracker.Utils.HelperUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class AsteroidService {

    @Value("${nasa.api.key}")
    private String apiKey;

    @Autowired
    private AIService aiService;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();


    public String getApiUrl() {
        return "https://api.nasa.gov/neo/rest/v1/feed?api_key=" + apiKey;
    }

    public AsteroidDataDTO getData() {
        ObjectMapper mapper = new ObjectMapper();
        AsteroidData asteroidData = new AsteroidData();
        try {
            asteroidData = mapper.readValue(new URL(getApiUrl()), AsteroidData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AsteroidDataDTO dto = AsteroidDataDTO.convertToDTO(asteroidData);
        dto.setAiSummary(generateAsteroidSummary(asteroidData));
        return dto;
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

    public List<NearEarthObjectsDTO> getFastestAsteroids(Integer topN) {
        List<NearEarthObjectsDTO> fastestAsteroids = new ArrayList<>();
        for (List<NearEarthObjectsDTO> asteroidList : getData().getNearEarthObjects().values()) {
            fastestAsteroids.addAll(asteroidList);
        }
        fastestAsteroids.sort(Comparator.comparingDouble(fastestAsteroid -> fastestAsteroid.getCloseApproachData().get(0).getRelativeVelocity().getKilometersPerHour()));
        return fastestAsteroids.subList(0, Math.min(topN, fastestAsteroids.size())).reversed();
    }

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

    public AsteroidData addSummaryToNearEarthObjects(AsteroidData data) {
        Map<String, List<NearEarthObjects>> updatedNearEarthObjectMap = data.getNearEarthObjects().entrySet()
                .parallelStream() // Process map entries in parallel
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> processAsteroids(entry.getValue()) // Process asteroids concurrently
                ));

        data.setNearEarthObjects(updatedNearEarthObjectMap);
        return data;
    }

    private List<NearEarthObjects> processAsteroids(List<NearEarthObjects> asteroids) {
        List<CompletableFuture<NearEarthObjects>> futures = asteroids.stream()
                .map(asteroid -> CompletableFuture.supplyAsync(() -> {
                    asteroid.setAiSummary(aiService.promptWithPathVariable(asteroid.toString()));
                    return asteroid;
                }, executor))
                .toList(); // Collect futures

        // Wait for all tasks to complete and collect results
        return futures.stream().map(CompletableFuture::join).toList();
    }


    public String generateAsteroidSummary(AsteroidData data) {
        StringBuilder jsonBuilder = new StringBuilder();

        // Append asteroid details to the StringBuilder
        for (List<NearEarthObjects> asteroids : data.getNearEarthObjects().values()) {
            for (NearEarthObjects asteroid : asteroids) {
                jsonBuilder.append(asteroid.toString()); // Append asteroid details
            }
        }

        Map<String, Object> requestMap = new HashMap<>();
        List<Map<String, Object>> contentsList = new ArrayList<>();
        Map<String, Object> contentsMap = new HashMap<>();

        List<Map<String, String>> partsList = new ArrayList<>();
        Map<String, String> partsMap = new HashMap<>();

        // Set text content with appended asteroid data
        String text = "Summarize the following asteroid data in a brief paragraph for a layman. "
                + "Highlight only asteroids visible to the naked eye, posing a potential threat, "
                + jsonBuilder;

        partsMap.put("text", text);
        partsList.add(partsMap);

        contentsMap.put("parts", partsList);
        contentsList.add(contentsMap);

        requestMap.put("contents", contentsList);

        try {
            // Use Jackson's ObjectMapper to convert the requestMap to a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestMap);

            // Call the Gemini API with the generated JSON string
            return aiService.callGeminiAPI(jsonRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating the request.";
        }
    }
}