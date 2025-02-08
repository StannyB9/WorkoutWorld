package com.workoutapp.repository;

import com.workoutapp.entity.GymEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymEquipmentRepository extends JpaRepository<GymEquipment, Long> {
}
