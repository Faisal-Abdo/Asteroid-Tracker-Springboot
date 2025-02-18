package com.example.AsteroidTracker.Repositories;

import com.example.AsteroidTracker.Models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}
