package com.workoutapp.serviceImpl;

import com.workoutapp.constant.ErrorMessage;
import com.workoutapp.dto.user.UserProfileDtoResponse;
import com.workoutapp.dto.user.UserUpdateRequestDto;
import com.workoutapp.dto.user.UserVO;
import com.workoutapp.entity.User;
import com.workoutapp.enums.Role;
import com.workoutapp.exception.WrongIdException;
import com.workoutapp.repository.UserRepository;
import com.workoutapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     * Autowired mapper.
     */
    private final ModelMapper modelMapper;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserProfileDtoResponse getUserProfileInformation(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new WrongIdException(ErrorMessage.USER_NOT_FOUND_BY_ID + userId));
        return modelMapper.map(user, UserProfileDtoResponse.class);
    }

    @Override
    public String generateToken(Authentication authentication) {
        // Token generation logic (e.g., using JWT)
        return "mocked-jwt-token";
    }

    @Override
    public void registerUser(String email, String password, String name, String role) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already use");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .role(Role.valueOf(role.toUpperCase()))
                .build();
        userRepository.save(user);
    }

    @Override
    public UserVO updateUserProfile(Long userId, UserUpdateRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setProfilePicturePath(dto.getProfilePicturePath());
        user.setName(dto.getName());
        user.setCity(dto.getCity());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserVO.class);
    }
}
