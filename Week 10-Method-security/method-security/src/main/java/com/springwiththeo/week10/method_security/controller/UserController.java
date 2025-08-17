package com.springwiththeo.week10.method_security.controller;

import com.springwiththeo.week10.method_security.repository.User;
import com.springwiththeo.week10.method_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    // The above line ensures that only the user with the given ID or an admin can access
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
}