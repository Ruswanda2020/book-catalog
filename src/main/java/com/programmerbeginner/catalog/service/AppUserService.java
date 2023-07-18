package com.programmerbeginner.catalog.service;

import com.programmerbeginner.catalog.dto.UserDetailResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {

    public UserDetails loadUserByUsername(String username);

    public UserDetailResponseDto findUserDetail();
}
