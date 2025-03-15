package com.example.AsteroidTracker.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String GEMINI_API_KEY;

    private final Logger logger = LoggerFactory.getLogger(AIService.class);

    ChatClient chatClient;

    public String getApiUrl(){
        return "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + GEMINI_API_KEY;
    };

    public AIService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public String promptWithPathVariable(String chat) {
        try {

            logger.info("Chat data: " + chat);
            String response = chatClient
                    .prompt("Summarise following Asteroid Data in 4 lines for simple layman humans: " + chat)
                    .call()
                    .content();
            logger.info("response: " + response);
            return response;
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }


    public String callGeminiAPI(String json) {
        RestTemplate restTemplate = new RestTemplate();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        logger.info(json);
        // Wrap request body
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        // Make POST request
        ResponseEntity<String> response = restTemplate.exchange(getApiUrl(), HttpMethod.POST, entity, String.class);

        return extractTextFromResponse(response.getBody()); // Return the API response
    }

    public String extractTextFromResponse(String response) {
        try {
            // Initialize Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse the response string into a JsonNode
            JsonNode rootNode = objectMapper.readTree(response);

            // Navigate to the "candidates" -> "content" -> "parts" -> "text"
            JsonNode candidatesNode = rootNode.path("candidates");
            if (candidatesNode.isArray() && candidatesNode.size() > 0) {
                JsonNode contentNode = candidatesNode.get(0).path("content");
                JsonNode partsNode = contentNode.path("parts");
                if (partsNode.isArray() && partsNode.size() > 0) {
                    JsonNode textNode = partsNode.get(0).path("text");
                    return textNode.asText(); // Return the text content
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if any issue occurs
    }
}