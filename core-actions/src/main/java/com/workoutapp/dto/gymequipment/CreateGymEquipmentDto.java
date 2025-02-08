package com.workoutapp.dto.gymequipment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGymEquipmentDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private String imageUrl;
}
