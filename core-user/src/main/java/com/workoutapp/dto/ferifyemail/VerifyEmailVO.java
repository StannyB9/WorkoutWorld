package com.workoutapp.dto.ferifyemail;

import com.workoutapp.dto.user.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyEmailVO {
    private Long id;

    private UserVO user;

    private String token;

    private LocalDateTime expiryDate;
}
