package com.programmerbeginner.catalog.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmerbeginner.catalog.dto.LoginRequestDto;
import com.programmerbeginner.catalog.exception.BadRequestException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class UserNamePasswordAuthProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    private final AuthenticationSuccessHandler successHandler;

    private final AuthenticationFailureHandler failureHandler;
    public UserNamePasswordAuthProcessingFilter(String defaultFilterProcessesUrl,ObjectMapper objectMapper,
                                                   AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super(defaultFilterProcessesUrl);
        this.objectMapper = objectMapper;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        LoginRequestDto dto = objectMapper.readValue(request.getReader(),LoginRequestDto.class);
        if(StringUtils.isBlank(dto.getUserName()) || StringUtils.isBlank(dto.getPassword())){
            throw  new BadRequestException("username.password.should.be.provided");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUserName(),dto.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
       this.successHandler.onAuthenticationSuccess(request, response,chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
