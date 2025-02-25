package com.example.AsteroidTracker.DTOs;

import com.example.AsteroidTracker.Models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequestDTO {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Role is required")
    private Role role;

    public @NotBlank(message = "Username is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is required") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    public @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email format") String email) {
        this.email = email;
    }

    public @NotNull(message = "Role is required") Role getRole() {
        return role;
    }

    public void setRole(@NotNull(message = "Role is required") Role role) {
        this.role = role;
    }
}