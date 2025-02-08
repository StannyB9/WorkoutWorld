package com.workoutapp.dto.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto {
    private Long id;
    private String name;
    private String description;
    private String videoUrl;
}
