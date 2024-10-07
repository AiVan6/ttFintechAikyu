package com.example.ttwebzaim.repositories;

import com.example.ttwebzaim.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

//    @Query("SELECT s.value FROM Settings s WHERE s.name = 'distanceRatioThreshold'")
//    Double findDistanceRatioThreshold();
    Optional<Settings> findByName(@Param("name")String name);
}
