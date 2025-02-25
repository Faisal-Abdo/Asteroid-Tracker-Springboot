package com.example.AsteroidTracker.Services;

import com.example.AsteroidTracker.DTOs.SignInRequestDTO;
import com.example.AsteroidTracker.DTOs.SignUpRequestDTO;
import com.example.AsteroidTracker.Models.User;
import com.example.AsteroidTracker.Config.UserRepository;
import com.example.AsteroidTracker.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    // Sign-in method for JWT generation
    public String signIn(SignInRequestDTO dto) throws Exception {
        // Find user by username
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(
                ()-> new Exception("Invalid username or password")
        );
        // Check password match
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new Exception("Invalid username or password");
        }

        // Generate JWT token
        return jwtUtil.generateToken(user.getUsername(), user.getEmail(), user.getRole().name());
    }

    public void signUp(SignUpRequestDTO dto) throws Exception {
        if (userRepository.findByUsername(dto.getUsername()) != null) {
            throw new Exception("Username already exists");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new Exception("Email already exists");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setUsername(dto.getUsername());

        userRepository.save(user);
    }
}