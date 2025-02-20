package com.example.AsteroidTracker.Models;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "users") // Explicitly specify the table name for clarity
public class User extends BaseEntity {

    @NotBlank(message = "Email is mandatory")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Use EnumType.STRING for storing role as a string in the database
    @Column(nullable = false)
    private Role role;

    private String username;}
