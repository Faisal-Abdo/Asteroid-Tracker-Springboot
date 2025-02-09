package com.example.AsteroidTracker.Repositories;

import com.example.AsteroidTracker.Models.Asteroid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//Mapping entities to databases
@Repository
public interface AsteroidRepository extends JpaRepository<Asteroid, Integer> {

    @Query("select a from Asteroids a where a.id=:id")
    Asteroid getAsteroidById(@Param(value = "id") Integer id);

    @Query("select a from Asteroids a where a.startDate=CURRENT_DATE and a.endDate=CURRENT_DATE")
    List<Asteroid> getAsteroidsByTodayDate();

    @Query("select a from Asteroids a where a.startDate=:startDate and a.endDate=:endDate")
    List<Asteroid> getAsteroidsWithinRange(@Param(value = "startDate") LocalDate startDate,
                                           @Param(value = "endDate") LocalDate endDate);

    @Query("select a from Asteroids a where a.size > :size")
    List<Asteroid> getAsteroidLargerThan(@Param(value = "size") Double size);

    @Query("select a from Asteroids a where a.riskOfCollision = true")
    List<Asteroid> getPotentialHazardousAsteroids();

    @Query(value = "select a from Asteroids a order by a.distance ASC limit=:count ", nativeQuery = true)
    List<Asteroid> getClosestAsteroids(@Param(value = "count") Integer count);

    @Query(value = "select a from Asteroids a order by a.speed DESC limit=:count", nativeQuery = true)
    List<Asteroid> getFastestAsteroids(@Param(value = "count") Integer count);

    /*@Query(value = "select a from Asteroids a order by a.size DESC limit=:count", nativeQuery = true)
    List<Asteroid> getLargestAsteroids(@Param(value = "count") Integer count);*/
}