package com.programmerbeginner.catalog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmerbeginner.catalog.security.filter.UserNamePasswordAuthProcessingFilter;
import com.programmerbeginner.catalog.security.handler.UserNamePasswordAuthFailureHandler;
import com.programmerbeginner.catalog.security.handler.UserNamePasswordAuthSuccessHandler;
import com.programmerbeginner.catalog.security.provider.UserNamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{

    private static final String AUTH_URL = "/v1/login";
    @Autowired
    private UserNamePasswordAuthProvider userNamePasswordAuthProvider;

    @Bean
    public AuthenticationSuccessHandler successHandler(ObjectMapper objectMapper){
        return new UserNamePasswordAuthSuccessHandler(objectMapper);
    }
    @Bean
    public AuthenticationFailureHandler failureHandler(ObjectMapper objectMapper){
        return new UserNamePasswordAuthFailureHandler(objectMapper);
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public UserNamePasswordAuthProcessingFilter processingFilter(ObjectMapper objectMapper,
                                                                 UserNamePasswordAuthSuccessHandler successHandler,
                                                                 UserNamePasswordAuthFailureHandler failureHandler,
                                                                 AuthenticationManager authenticationManager){
        UserNamePasswordAuthProcessingFilter filter = new UserNamePasswordAuthProcessingFilter(AUTH_URL,objectMapper,successHandler,failureHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Autowired
    public void registerProvider(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userNamePasswordAuthProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserNamePasswordAuthProcessingFilter authProcessingFilter) throws Exception {
        http
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().httpBasic();
                 http.addFilterBefore(authProcessingFilter, UsernamePasswordAuthenticationFilter.class);
        return http.getOrBuild();
    }
}
