package com.workoutapp.service;

import com.workoutapp.dto.user.UserProfileDtoResponse;
import com.workoutapp.dto.user.UserUpdateRequestDto;
import com.workoutapp.dto.user.UserVO;
import com.workoutapp.entity.User;
import org.springframework.security.core.Authentication;

public interface UserService {

    User findByEmail(String email);

    UserProfileDtoResponse getUserProfileInformation(Long userId);

    String generateToken(Authentication authentication);

    void registerUser(String email, String password, String name, String role);

    UserVO updateUserProfile(Long userId, UserUpdateRequestDto dto);
}
