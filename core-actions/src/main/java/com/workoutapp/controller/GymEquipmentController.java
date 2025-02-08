package com.workoutapp.controller;

import com.workoutapp.constant.HttpStatuses;
import com.workoutapp.dto.gymequipment.CreateGymEquipmentDto;
import com.workoutapp.dto.gymequipment.GymEquipmentDto;
import com.workoutapp.service.GymEquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gym-equipment")
public class GymEquipmentController {

    private final GymEquipmentService gymEquipmentService;

    public GymEquipmentController(GymEquipmentService gymEquipmentService) {
        this.gymEquipmentService = gymEquipmentService;
    }

    @Operation(summary = "Get all gym equipment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @GetMapping
    public ResponseEntity<List<GymEquipmentDto>> getAllEquipment() {
        return ResponseEntity.ok(gymEquipmentService.getAllGymEquipment());
    }

    @Operation(summary = "Get gym equipment by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GymEquipmentDto> getGymEquipmentById(@PathVariable Long id) {
        return ResponseEntity.ok(gymEquipmentService.getGymEquipmentById(id));
    }

    @Operation(summary = "Add new gym equipment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @PostMapping
    public ResponseEntity<GymEquipmentDto> addGymEquipment(@RequestBody @Valid CreateGymEquipmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gymEquipmentService.addGymEquipment(dto));
    }

    @Operation(summary = "Delete an exist gym equipment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        gymEquipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}
