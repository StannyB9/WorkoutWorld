package com.workoutapp.service;

import com.workoutapp.dto.exercise.CreateExerciseDto;
import com.workoutapp.dto.exercise.ExerciseDto;

import java.util.List;

public interface ExerciseService {

    List<ExerciseDto> getAllExercises();

    ExerciseDto getExerciseById(Long id);

    ExerciseDto createExercise(CreateExerciseDto dto);

    void deleteExercise(Long id);
}
