package com.example.AsteroidTracker.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AIService {


    private final Logger logger = LoggerFactory.getLogger(AIService.class);

    ChatClient chatClient;

    public AIService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public String promptWithPathVariable(String chat) {
        try {

            logger.info(chat);
            String response = chatClient
                    .prompt("Summarise following Asteroid Data in 4 lines for simple layman humans: " + chat)
                    .call()
                    .content();

            return response;
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}
