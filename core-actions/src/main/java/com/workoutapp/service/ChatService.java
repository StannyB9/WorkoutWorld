package com.workoutapp.service;

import com.workoutapp.dto.chat.ChatDto;

import java.util.List;

public interface ChatService {
    List<ChatDto> getAllChats();

    ChatDto getChatById(Long id);

    ChatDto createChat(ChatDto chatDto);

    ChatDto updateChat(Long id, ChatDto chatDto);

    void deleteChat(Long id);
}
