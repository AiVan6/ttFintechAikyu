package com.example.ttwebzaim.repositories;

import com.example.ttwebzaim.entitys.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    @Query("SELECT s.value FROM Settings s WHERE s.name = 'distanceRatioThreshold'")
    Double findDistanceRatioThreshold();

}
