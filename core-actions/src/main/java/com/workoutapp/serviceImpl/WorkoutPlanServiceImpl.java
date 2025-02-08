package com.workoutapp.serviceImpl;

import com.workoutapp.dto.workoutplan.CreateWorkoutPlanDto;
import com.workoutapp.dto.workoutplan.WorkoutPlanDto;
import com.workoutapp.entity.Exercise;
import com.workoutapp.entity.WorkoutPlan;
import com.workoutapp.repository.ExerciseRepository;
import com.workoutapp.repository.WorkoutPlanRepository;
import com.workoutapp.service.WorkoutPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public WorkoutPlanServiceImpl(WorkoutPlanRepository workoutPlanRepository,
                                  ExerciseRepository exerciseRepository,
                                  ModelMapper modelMapper) {
        this.workoutPlanRepository = workoutPlanRepository;
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WorkoutPlanDto> getWorkoutPlansByUser(Long userId) {
        List<WorkoutPlan> plans = workoutPlanRepository.findByUserId(userId);
        return plans.stream()
                .map(plan -> modelMapper.map(plan, WorkoutPlanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public WorkoutPlanDto createWorkoutPlan(CreateWorkoutPlanDto dto) {
        WorkoutPlan plan = new WorkoutPlan();
        plan.setName(dto.getName());
        plan.setDescription(dto.getDescription());
        List<Exercise> exercises = dto.getExerciseIds().stream()
                .map(id -> exerciseRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + id)))
                .collect(Collectors.toList());
        plan.setExercises(exercises);
        WorkoutPlan saved = workoutPlanRepository.save(plan);
        return modelMapper.map(saved, WorkoutPlanDto.class);
    }

    @Override
    public WorkoutPlanDto updateWorkoutPlan(Long id, CreateWorkoutPlanDto dto) {
        WorkoutPlan existingPlan = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkoutPlan not found with id: " + id));
        existingPlan.setName(dto.getName());
        existingPlan.setDescription(dto.getDescription());
        List<Exercise> exercises = dto.getExerciseIds().stream()
                .map(eid -> exerciseRepository.findById(eid)
                        .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + eid)))
                .collect(Collectors.toList());
        existingPlan.setExercises(exercises);
        WorkoutPlan updated = workoutPlanRepository.save(existingPlan);
        return modelMapper.map(updated, WorkoutPlanDto.class);
    }

    @Override
    public void deleteWorkoutPlan(Long id) {
        workoutPlanRepository.deleteById(id);
    }

    @Override
    public WorkoutPlanDto getWorkoutPlanById(Long id) {
        WorkoutPlan plan = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkoutPlan not found with id: " + id));
        return modelMapper.map(plan, WorkoutPlanDto.class);
    }
}
