package com.workoutapp.serviceImpl;

import com.workoutapp.dto.gymequipment.CreateGymEquipmentDto;
import com.workoutapp.dto.gymequipment.GymEquipmentDto;
import com.workoutapp.entity.GymEquipment;
import com.workoutapp.repository.GymEquipmentRepository;
import com.workoutapp.service.GymEquipmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GymEquipmentServiceImpl implements GymEquipmentService {

    private final GymEquipmentRepository gymEquipmentRepository;
    private final ModelMapper modelMapper;

    public GymEquipmentServiceImpl(GymEquipmentRepository gymEquipmentRepository, ModelMapper modelMapper) {
        this.gymEquipmentRepository = gymEquipmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GymEquipmentDto> getAllGymEquipment() {
        return gymEquipmentRepository.findAll().stream()
                .map(eq -> modelMapper.map(eq, GymEquipmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GymEquipmentDto getGymEquipmentById(Long id) {
        GymEquipment equipment = gymEquipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gym equipment not found with id: " + id));
        return modelMapper.map(equipment, GymEquipmentDto.class);
    }

    @Override
    public GymEquipmentDto addGymEquipment(CreateGymEquipmentDto dto) {
        GymEquipment equipment = modelMapper.map(dto, GymEquipment.class);
        GymEquipment saved = gymEquipmentRepository.save(equipment);
        return modelMapper.map(saved, GymEquipmentDto.class);
    }

    @Override
    public void deleteEquipment(Long id) {
        gymEquipmentRepository.deleteById(id);
    }
}
