package com.workoutapp.dto.chat;

import com.workoutapp.dto.message.MessageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
    private Long id;
    private Long userId;
    private Long trainerId;
    private List<MessageDto> message;
}
