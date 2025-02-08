package com.workoutapp.service;


import com.workoutapp.dto.gymequipment.CreateGymEquipmentDto;
import com.workoutapp.dto.gymequipment.GymEquipmentDto;

import java.util.List;

public interface GymEquipmentService {

    List<GymEquipmentDto> getAllGymEquipment();

    GymEquipmentDto getGymEquipmentById(Long id);

    GymEquipmentDto addGymEquipment(CreateGymEquipmentDto dto);

    void deleteEquipment(Long id);
}
