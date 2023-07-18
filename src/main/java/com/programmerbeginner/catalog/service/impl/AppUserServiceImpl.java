package com.programmerbeginner.catalog.service.impl;

import com.programmerbeginner.catalog.domain.AppUser;
import com.programmerbeginner.catalog.exception.ResourceNotFound;
import com.programmerbeginner.catalog.repository.AppUserRepository;
import com.programmerbeginner.catalog.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                  .orElseThrow(()-> new ResourceNotFound("invalid username"));
    }
}
