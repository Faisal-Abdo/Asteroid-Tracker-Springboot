package com.example.AsteroidTracker.Repositories;

import com.example.AsteroidTracker.Models.EstimatedDiameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimatedDiameterRepository  extends JpaRepository<EstimatedDiameter,Integer> {
}
