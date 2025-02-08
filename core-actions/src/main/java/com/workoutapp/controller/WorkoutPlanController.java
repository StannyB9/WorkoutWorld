package com.workoutapp.controller;

import com.workoutapp.constant.HttpStatuses;
import com.workoutapp.dto.workoutplan.CreateWorkoutPlanDto;
import com.workoutapp.dto.workoutplan.WorkoutPlanDto;
import com.workoutapp.service.WorkoutPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-plans")
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;

    public WorkoutPlanController(WorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }

    /**
     * Get all workout plans for a specific user
     *
     * @param userId ID user
     * @return List of workout plans
     */
    @Operation(summary = "Get workout plan by user ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/{userId}")
    public ResponseEntity<List<WorkoutPlanDto>> getWorkoutPlansByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(workoutPlanService.getWorkoutPlansByUser(userId));
    }

    /**
     * Create a new workout plan
     *
     * @param dto workout plan
     * @return Created workout plan
     */
    @Operation(summary = "Create a new workout plan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @PostMapping
    public ResponseEntity<WorkoutPlanDto> createWorkoutPlan(@RequestBody @Valid CreateWorkoutPlanDto dto) {
        WorkoutPlanDto created = workoutPlanService.createWorkoutPlan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Update the existing workout plan
     *
     * @param id  plan ID
     * @param dto updated plan data
     * @return Updated plan
     */
    @Operation(summary = "Update an exist workout plan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlanDto> updateWorkoutPlan(@PathVariable Long id, @RequestBody @Valid CreateWorkoutPlanDto dto) {
        WorkoutPlanDto updated = workoutPlanService.updateWorkoutPlan(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete workout plan
     *
     * @param id plan Id
     * @return HTTP status
     */
    @Operation(summary = "Delete an exist workout plan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
        workoutPlanService.deleteWorkoutPlan(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get workout plan by ID
     *
     * @param id plan ID
     * @return workout plan
     */
    @Operation(summary = "Get workout plan by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/plan/{id}")
    public ResponseEntity<WorkoutPlanDto> getWorkoutPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutPlanService.getWorkoutPlanById(id));
    }
}
