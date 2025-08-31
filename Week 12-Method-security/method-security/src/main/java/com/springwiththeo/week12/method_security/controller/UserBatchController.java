package com.springwiththeo.week12.method_security.controller;

import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserBatchController {

    @PostMapping("/batch")
    @PreFilter("filterObject == authentication.principal.id or hasRole('ADMIN')")
    public List<Long> batch(@RequestBody List<Long> ids) {
        return ids;
    }
}
