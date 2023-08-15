package com.programmerbeginner.catalog.service;

import com.programmerbeginner.catalog.dto.UserResponseDetailDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {


    public UserResponseDetailDto findUserDetail();

}
