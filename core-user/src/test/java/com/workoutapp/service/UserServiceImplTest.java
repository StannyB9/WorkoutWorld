package com.workoutapp.service;

import com.workoutapp.dto.user.UserProfileDtoResponse;
import com.workoutapp.dto.user.UserUpdateRequestDto;
import com.workoutapp.dto.user.UserVO;
import com.workoutapp.entity.User;
import com.workoutapp.enums.Role;
import com.workoutapp.exception.WrongIdException;
import com.workoutapp.repository.UserRepository;
import com.workoutapp.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(1L)
                .email("john@doe.com")
                .password("passsword")
                .name("John Doe")
                .role(Role.ROLE_USER)
                .build();
    }

    @Test
    void testFindByEmail_UserExists() {
        when(userRepository.findByEmail("john@doe.com")).thenReturn(testUser);
        User foundUser = userService.findByEmail("john@doe.com");
        assertNotNull(foundUser);
        assertEquals("john@doe.com", foundUser.getEmail());
    }

    @Test
    void testFindByEmail_UserDoesNotExist() {
        when(userRepository.findByEmail("jane@doe.com")).thenReturn(null);
        User foundUser = userService.findByEmail("jane@doe.com");
        assertNull(foundUser);
    }

    @Test
    void testUserProfileInformation_UserExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        UserProfileDtoResponse mockResponse = new UserProfileDtoResponse();
        when(modelMapper.map(testUser, UserProfileDtoResponse.class)).thenReturn(mockResponse);
        UserProfileDtoResponse response = userService.getUserProfileInformation(1L);
        assertNotNull(response);
    }

    @Test
    void testGetUserProfileInformation_UserNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(WrongIdException.class, () -> userService.getUserProfileInformation(2L));
    }

    @Test
    void testGenerateToken() {
    }

    @Test
    void testRegisterUser_Success() {
        when(userRepository.existsByEmail("new@user.com")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        userService.registerUser("new@user.com", "password123", "New User", "ROLE_USER");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_EmailAlreadyExist() {
        when(userRepository.existsByEmail("john@doe.com")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser("john@doe.com", "password", "John Doe", "ROLE_USER"));
    }

    @Test
    void testUpdateUserProfile_UserExist() {
        UserUpdateRequestDto updateDto = new UserUpdateRequestDto("https://example.com/images/newprofile.jpg", "Updated Name", "New City");
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        when(modelMapper.map(testUser, UserVO.class)).thenReturn(new UserVO());
        UserVO response = userService.updateUserProfile(1L, updateDto);
        assertNotNull(response);
    }

    @Test
    void testUpdateUserProfile_UserNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        UserUpdateRequestDto updateDto = new UserUpdateRequestDto("https://example.com/images/newprofile.jpg", "Updated Name", "New City");
        assertThrows(RuntimeException.class, () -> userService.updateUserProfile(2L, updateDto));
    }
}
