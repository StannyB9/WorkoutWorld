package com.workoutapp.dto.exercise;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExerciseDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private String videoUrl;
}
