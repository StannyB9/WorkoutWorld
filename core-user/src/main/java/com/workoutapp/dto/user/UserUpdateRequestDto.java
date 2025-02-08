package com.workoutapp.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO for updating user profile")
public class UserUpdateRequestDto {

    @Schema(description = "URL or path to the profile picture", example = "https://example.com/images/profile.jpg")
    private String profilePicturePath;

    @NotBlank(message = "Name cannot be blank")
    @Schema(description = "User Name", example = "John")
    private String name;

    @Schema(description = "User City", example = "Kyiv")
    private String city;
}
