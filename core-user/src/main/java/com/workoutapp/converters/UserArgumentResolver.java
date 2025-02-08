package com.workoutapp.converters;

import com.workoutapp.annotations.CurrentUser;
import com.workoutapp.dto.user.UserVO;
import com.workoutapp.entity.User;
import com.workoutapp.exception.UnauthorizedException;
import com.workoutapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;

@Component
@AllArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUser.class) != null
                && parameter.getParameterType().equals(UserVO.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Principal principal = webRequest.getUserPrincipal();
        if (principal == null) {
            throw new UnauthorizedException("User is not authenticated");
        }

        User user = userService.findByEmail(principal.getName());
        if (user == null) {
            throw new EntityNotFoundException("User not found for email: " + principal.getName());
        }

        return modelMapper.map(user, UserVO.class);
    }
}
