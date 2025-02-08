package com.workoutapp.dto.ownsecurity;

import com.workoutapp.dto.user.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnSecurityVO {
    private Long id;

    private String password;

    private UserVO user;
}
