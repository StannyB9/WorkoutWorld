package com.workoutapp.dto.user;

import com.workoutapp.dto.ferifyemail.VerifyEmailVO;
import com.workoutapp.dto.ownsecurity.OwnSecurityVO;
import com.workoutapp.enums.EmailNotification;
import com.workoutapp.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "User name", example = "John")
    private String name;

    @Schema(description = "User email", example = "user@example.com")
    private String email;

    @Schema(description = "User role", example = "ROLE_USER")
    private String role;

    @Schema(description = "User rating", example = "4.2")
    private Double rating;

    @Schema(description = "Current status of the user account")
    private UserStatus userStatus;

    //???//
    @Schema(description = "User's email notification preference")
    private EmailNotification emailNotification;

    @Schema(description = "Registration date and time of the user", example = "2023-01-01T12:00:00")
    private LocalDateTime dateOfRegistration;

    @Schema(description = "URL or file path to the user's profile picture", example = "https://example.com/images/profile.jpg")
    private String profilePicturePath;

    @Schema(description = "User city", example = "Kyiv")
    private String city;

    @Schema(description = "Timestamp of the last activity of the user", example = "2023-01-02T15:30:00")
    private LocalDateTime lastActivityTime;


}
