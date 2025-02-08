package com.workoutapp.serviceImpl;

import com.workoutapp.dto.exercise.CreateExerciseDto;
import com.workoutapp.dto.exercise.ExerciseDto;
import com.workoutapp.entity.Exercise;
import com.workoutapp.repository.ExerciseRepository;
import com.workoutapp.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExerciseDto> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream()
                .map(ex -> modelMapper.map(ex, ExerciseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExerciseDto getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + id));
        return modelMapper.map(exercise, ExerciseDto.class);
    }

    @Override
    public ExerciseDto createExercise(CreateExerciseDto dto) {
        Exercise exercise = modelMapper.map(dto, Exercise.class);
        Exercise saved = exerciseRepository.save(exercise);
        return modelMapper.map(saved, ExerciseDto.class);
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
