package com.example.AsteroidTracker.Config;

import com.example.AsteroidTracker.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("Select u from User u where u.username=:username")
    Optional<User> findByUsername(@Param(value = "username") String username);

    Boolean existsByEmail(String email);
}
