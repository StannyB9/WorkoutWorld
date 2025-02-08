package com.workoutapp.repository;

import com.workoutapp.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
    List<WorkoutPlan> findByUserId(Long userId);
}
