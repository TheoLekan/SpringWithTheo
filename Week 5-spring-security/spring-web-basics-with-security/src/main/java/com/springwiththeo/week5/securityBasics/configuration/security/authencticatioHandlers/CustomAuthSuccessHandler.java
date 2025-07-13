package com.springwiththeo.week5.securityBasics.configuration.security.authencticatioHandlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("User " + authentication.getName() + " logged in successfully");
        // Redirect to the admin page on successful login
        response.setStatus(HttpServletResponse.SC_OK); // Set the response status to 200 OK
        response.setContentType("application/json"); // Set the response content type to JSON
        response.getWriter().write("{\"message\": \"Login successful\"}");
    }
}
