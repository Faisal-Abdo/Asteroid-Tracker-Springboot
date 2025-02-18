package com.example.AsteroidTracker.Repositories;

import com.example.AsteroidTracker.Models.AsteroidData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsteroidDataRepository extends JpaRepository<AsteroidData,Integer> {


}
