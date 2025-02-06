package com.example.AsteroidTracker.Repositories;

import com.example.AsteroidTracker.Models.Asteroid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//Mapping entities to databases
@Repository
public interface AsteroidRepository extends JpaRepository<Asteroid, Integer> {

    Asteroid getAsteroidById(@Param(value = "id") Integer id);

    List<Asteroid> getAsteroidsByTodayDate();

    List<Asteroid> getAsteroidsWithinRange(@Param(value = "startDate") LocalDate startDate,
                                           @Param(value = "endDate") LocalDate endDate);

    List<Asteroid> getAsteroidLargerThan(@Param(value = "size") Double size);

    List<Asteroid> getPotentialHazardousAsteroids(@Param(value = "riskOfCollision")
                                                  Boolean riskOfCollision);

    List<Asteroid> getClosestAsteroids(@Param(value = "distance") Long distance);

    List<Asteroid> getFastestAsteroids(@Param(value = "speed") Double speed);

    List<Asteroid> getLargestAsteroids(@Param(value = "size") Double size);
}