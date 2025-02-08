package com.workoutapp.dto.workoutplan;

import com.workoutapp.dto.exercise.ExerciseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutPlanDto {
    private Long id;
    private String name;
    private String description;
    private List<ExerciseDto> exercises;
}
