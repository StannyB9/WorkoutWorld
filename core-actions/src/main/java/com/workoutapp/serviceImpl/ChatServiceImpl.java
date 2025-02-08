package com.workoutapp.serviceImpl;

import com.workoutapp.dto.chat.ChatDto;
import com.workoutapp.entity.Chat;
import com.workoutapp.entity.User;
import com.workoutapp.repository.ChatRepository;
import com.workoutapp.repository.UserRepository;
import com.workoutapp.service.ChatService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ChatServiceImpl(ChatRepository chatRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<ChatDto> getAllChats() {
        List<Chat> chats = chatRepository.findAll();
        return chats.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChatDto getChatById(Long id) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found by id: " + id));
        return convertToDto(chat);
    }

    @Override
    public ChatDto createChat(ChatDto chatDto) {
        Chat chat = convertToEntity(chatDto);
        Chat savedChat = chatRepository.save(chat);
        return convertToDto(savedChat);
    }

    @Override
    public ChatDto updateChat(Long id, ChatDto chatDto) {
        Chat existingChat = chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found by id: " + id));
        if (chatDto.getUserId() != null) {
            User user = userRepository.findById(chatDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + chatDto.getUserId()));
            existingChat.setUser(user);
        }
        if (chatDto.getTrainerId() != null) {
            User trainer = userRepository.findById(chatDto.getTrainerId())
                    .orElseThrow(() -> new RuntimeException("Trainer not found with id: " + chatDto.getTrainerId()));
        }
        Chat updatedChat = chatRepository.save(existingChat);
        return convertToDto(updatedChat);
    }

    @Override
    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }

    private ChatDto convertToDto(Chat chat) {
        ChatDto dto = modelMapper.map(chat, ChatDto.class);
        dto.setUserId(chat.getUser() != null ? chat.getUser().getId() : null);
        dto.setTrainerId(chat.getTrainer() != null ? chat.getTrainer().getId() : null);
        return dto;
    }

    private Chat convertToEntity(ChatDto dto) {
        Chat chat = modelMapper.map(dto, Chat.class);
        return chat;
    }
}
