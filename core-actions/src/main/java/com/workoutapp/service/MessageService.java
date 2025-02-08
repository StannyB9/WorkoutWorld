package com.workoutapp.service;

import com.workoutapp.dto.message.MessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto> getAllMessages();

    MessageDto getMessageById(Long id);

    MessageDto createMessage(MessageDto messageDto);

    MessageDto updateMessage(Long id, MessageDto messageDto);

    void deleteMessage(Long id);

    List<MessageDto> getAllMessageByChatId(Long chatId);
}
