package com.workoutapp.service;

import com.workoutapp.dto.workoutplan.CreateWorkoutPlanDto;
import com.workoutapp.dto.workoutplan.WorkoutPlanDto;

import java.util.List;

public interface WorkoutPlanService {

    List<WorkoutPlanDto> getWorkoutPlansByUser(Long userId);

    WorkoutPlanDto createWorkoutPlan(CreateWorkoutPlanDto dto);

    WorkoutPlanDto updateWorkoutPlan(Long id, CreateWorkoutPlanDto dto);

    void deleteWorkoutPlan(Long id);

    WorkoutPlanDto getWorkoutPlanById(Long id);
}
