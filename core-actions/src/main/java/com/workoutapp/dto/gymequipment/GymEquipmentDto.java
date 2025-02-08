package com.workoutapp.dto.gymequipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymEquipmentDto {
    private Long id;
    private String name;
    private String description;
    private String imagUrl;
}
