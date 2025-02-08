package com.workoutapp.controller;

import com.workoutapp.annotations.CurrentUser;
import com.workoutapp.constant.HttpStatuses;
import com.workoutapp.dto.user.UserProfileDtoResponse;
import com.workoutapp.dto.user.UserUpdateRequestDto;
import com.workoutapp.dto.user.UserVO;
import com.workoutapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method returns user profile information.
     *
     * @return {@link UserProfileDtoResponse}.
     * @author Stanislav Belchuk
     */
    @Operation(summary = "Get user profile information by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfileDtoResponse> getUserProfileInformation(
            @Parameter(description = "Id of current user cannot be empty.") @PathVariable Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserProfileInformation(userId));
    }

    /**
     * Updates the profile of current user.
     *
     * @param updateRequest profile update data.
     * @param currentUser   current user introduced via @CurrentUser
     * @return updated user profile
     */
    @Operation(summary = "Update current user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile update successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping("/profile/update")
    public ResponseEntity<UserVO> updateUserProfile(@RequestBody @Valid UserUpdateRequestDto updateRequest,
                                                    @Parameter(description = "The currently authenticated user")
                                                    @CurrentUser UserVO currentUser) {
        return ResponseEntity.ok(userService.updateUserProfile(currentUser.getId(), updateRequest));
    }
}
