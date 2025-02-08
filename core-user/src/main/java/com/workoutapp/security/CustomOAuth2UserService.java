package com.workoutapp.security;

import com.workoutapp.entity.User;
import com.workoutapp.enums.Role;
import com.workoutapp.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");

        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = registerNewUser(oAuth2User, userRequest);
        } else {
            user = updateExistingUser(user, oAuth2User);
        }
        return oAuth2User;
    }

    private User registerNewUser(OAuth2User oAuth2User, OAuth2UserRequest userRequest) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        User newUser = new User();
        newUser.setEmail((String) attributes.get("email"));
        newUser.setName((String) attributes.get("name"));
        newUser.setRole(Role.ROLE_USER);
        return userRepository.save(newUser);
    }

    private User updateExistingUser(User existingUser, OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        existingUser.setName((String) attributes.get("name"));
        return userRepository.save(existingUser);
    }
}
