package com.imon.jobapp.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest servletRequest,
            @NonNull HttpServletResponse servletResponse,
            @NonNull FilterChain filterChain) throws IOException, ServletException {

        String name = servletRequest.getHeader("name");
        if (name == null || !name.equals("imon")) {
            throw new CustomHeaderException("'name' header is missing in the header.please add the header.");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
