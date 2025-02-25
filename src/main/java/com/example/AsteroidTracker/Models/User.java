
package com.example.AsteroidTracker.Models;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "users") // Explicitly specify the table name for clarity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Long id; // Primary key

    @NotBlank(message = "Email is mandatory")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Use EnumType.STRING for storing role as a string in the database
    @Column(nullable = false)
    private Role role;

    @NotBlank(message = "Username is mandatory")
    @Column(nullable = false)
    private String username;

    public @NotBlank(message = "Email is mandatory") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is mandatory") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is mandatory") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is mandatory") String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public @NotBlank(message = "Username is mandatory") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is mandatory") String username) {
        this.username = username;
    }


}