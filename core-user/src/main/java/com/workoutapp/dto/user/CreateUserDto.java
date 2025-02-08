package com.workoutapp.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDto {

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Schema(description = "User email address", example = "user@example.com")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Schema(description = "User password", example = "MySecretPassword")
    private String password;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 20)
    @Schema(description = "User name", example = "John")
    private String name;

    @Schema(description = "User City", example = "Kyiv")
    private String city;

    @NotBlank(message = "Role cannot be empty")
    @Schema(description = "User role", example = "ROLE_USER")
    private String role;

    @Schema(description = "URL of the user's profile picture (optional)", example = "https://example.com/images/profile.jpg")
    private String profilePicturePath;
}
