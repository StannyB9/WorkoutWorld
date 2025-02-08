package com.workoutapp.controller;

import com.workoutapp.constant.HttpStatuses;
import com.workoutapp.dto.chat.ChatDto;
import com.workoutapp.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Get all chats
     *
     * @return List of chats
     */
    @Operation(summary = "Get all chats.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @GetMapping
    public ResponseEntity<List<ChatDto>> getAllChats() {
        return ResponseEntity.ok(chatService.getAllChats());
    }

    /**
     * Get chat by ID
     *
     * @param id chat ID
     * @return chat
     */
    @Operation(summary = "Get chat by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ChatDto> getChatById(@PathVariable Long id) {
        return ResponseEntity.ok(chatService.getChatById(id));
    }

    /**
     * Create a new chat
     *
     * @param chatDto chatDto
     * @return Created chat
     */
    @Operation(summary = "Create a new chat.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @PostMapping
    public ResponseEntity<ChatDto> createChat(@RequestBody ChatDto chatDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.createChat(chatDto));
    }

    /**
     * Update the existing chat
     *
     * @param id          chat ID
     * @param chatDto update chat
     * @return Updated chat
     */
    @Operation(summary = "Update an exist chat.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ChatDto> updateChat(@PathVariable Long id, @RequestBody ChatDto chatDto) {
        return ResponseEntity.ok(chatService.updateChat(id, chatDto));
    }

    /**
     * Delete chat
     *
     * @param id chat ID
     * @return HTTP status
     */
    @Operation(summary = "Delete an exist chat.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
