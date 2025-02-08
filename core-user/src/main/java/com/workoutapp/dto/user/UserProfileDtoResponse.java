package com.workoutapp.dto.user;

import com.workoutapp.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class UserProfileDtoResponse {

    @Schema(description = "URL of the user's profile picture (optional)", example = "https://example.com/images/profile.jpg")
    private String profilePicturePath;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 20)
    @Schema(description = "User name", example = "John")
    private String name;

    @Schema(description = "User City", example = "Kyiv")
    private String city;

    @Schema(description = "User rating", example = "4.2")
    private Float rating;

    @NotBlank(message = "Role cannot be empty")
    @Schema(description = "User role", example = "ROLE_USER")
    private Role role;
}
