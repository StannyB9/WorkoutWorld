package com.workoutapp.dto.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Long id;
    private Long senderId;
    private String context;
    @Schema(type = "string", format = "date-time", description = "Date and time of creating")
    private LocalDateTime timeStamp;
}
