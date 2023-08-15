package com.programmerbeginner.catalog.service.impl;

import com.programmerbeginner.catalog.dto.UserResponseDetailDto;
import com.programmerbeginner.catalog.exception.ResourceNotFound;
import com.programmerbeginner.catalog.repository.UserRepository;
import com.programmerbeginner.catalog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFound("username invalid"));
    }

    @Override
    public UserResponseDetailDto findUserDetail() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        UserResponseDetailDto dto = new UserResponseDetailDto();
        String username = ctx.getAuthentication().getName();
        dto.setUsername(username);
        return dto;
    }
}
