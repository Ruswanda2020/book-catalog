package com.programmerbeginner.catalog.config;

import com.programmerbeginner.catalog.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
     private AppUserService appUserService;

    @Autowired
    void registerProvider(AuthenticationManagerBuilder authenticationManager, PasswordEncoder passwordEncoder)
    throws Exception{
        authenticationManager.userDetailsService(appUserService).passwordEncoder(passwordEncoder);
    }

}
