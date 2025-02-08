package com.workoutapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String kindOfSport;

    @Column(nullable = false, name = "muscle_group")
    private String muscleGroup;

    @Column(nullable = false)
    private String difficulty;

    @org.hibernate.validator.constraints.URL
    private String mediaUrl;

    @ManyToOne
    @JoinColumn(name = "equipment_id", referencedColumnName = "id", nullable = false)
    private GymEquipment gymEquipment;
}
