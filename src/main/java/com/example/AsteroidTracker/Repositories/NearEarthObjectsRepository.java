package com.example.AsteroidTracker.Repositories;

import com.example.AsteroidTracker.Models.NearEarthObjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NearEarthObjectsRepository extends JpaRepository<NearEarthObjects,Integer> {
}
