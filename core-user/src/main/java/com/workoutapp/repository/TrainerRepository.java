package com.workoutapp.repository;

import com.workoutapp.entity.TrainerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<TrainerProfile, Long> {
}
