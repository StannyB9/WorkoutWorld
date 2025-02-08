package com.workoutapp.dto.workoutplan;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkoutPlanDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotEmpty
    private List<Long> exerciseIds;
}
