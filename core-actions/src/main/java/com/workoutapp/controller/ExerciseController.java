package com.workoutapp.controller;

import com.workoutapp.dto.exercise.CreateExerciseDto;
import com.workoutapp.dto.exercise.ExerciseDto;
import com.workoutapp.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    /**
     * Get all exercise
     *
     * @return List of exercise
     */
    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getAllExercises() {
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }

    /**
     * Get exercise by ID
     *
     * @param id exercise ID
     * @return exercise
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable Long id) {
        return ResponseEntity.ok(exerciseService.getExerciseById(id));
    }

    /**
     * Create a new exercise
     *
     * @param dto create exercise
     * @return Created exercise
     */
    @PostMapping
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody @Valid CreateExerciseDto dto) {
        ExerciseDto created = exerciseService.createExercise(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Delete exercise
     *
     * @param id exercise ID
     * @return HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}
