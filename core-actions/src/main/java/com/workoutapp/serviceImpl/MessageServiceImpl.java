package com.workoutapp.serviceImpl;

import com.workoutapp.dto.message.MessageDto;
import com.workoutapp.entity.Message;
import com.workoutapp.repository.MessageRepository;
import com.workoutapp.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDto getMessageById(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found by id: " + id));
        return convertToDto(message);
    }

    @Override
    public MessageDto createMessage(MessageDto messageDto) {
        Message message = convertToEntity(messageDto);
        Message savedMessage = messageRepository.save(message);
        return convertToDto(savedMessage);
    }

    @Override
    public MessageDto updateMessage(Long id, MessageDto messageDto) {
        Message existingMessage = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
        existingMessage.setContext(messageDto.getContext());
        existingMessage.setTimestamp(messageDto.getTimeStamp());
        Message updatedMessage = messageRepository.save(existingMessage);
        return convertToDto(updatedMessage);
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<MessageDto> getAllMessageByChatId(Long chatId) {
        List<Message> messages = messageRepository.findByChatId(chatId);
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MessageDto convertToDto(Message message) {
        MessageDto dto = modelMapper.map(message, MessageDto.class);
        dto.setSenderId(message.getSender() != null ? message.getSender().getId() : null);
        return dto;
    }

    private Message convertToEntity(MessageDto dto) {
        Message message = modelMapper.map(dto, Message.class);
        return message;
    }
}
