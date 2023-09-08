package com.programmerbeginner.catalog.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Hello from filter {}",servletRequest.getLocalAddr());
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
