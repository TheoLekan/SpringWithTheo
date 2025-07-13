package com.springwiththeo.week5.securityBasics.configuration.security.authencticatioHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("Login failed: " + exception.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Set the response status to 401 Unauthorized
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // Set the response content type to plain text
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("error", exception.getMessage());
        response.getWriter().write(mapper.writeValueAsString(stringObjectHashMap));
    }
}
