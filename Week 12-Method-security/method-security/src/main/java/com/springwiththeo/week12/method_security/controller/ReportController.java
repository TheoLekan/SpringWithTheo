package com.springwiththeo.week12.method_security.controller;

import com.springwiththeo.week12.method_security.model.Report;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @GetMapping("/{id}")
    @PostAuthorize("returnObject.ownerId() == authentication.principal.id or hasRole('ADMIN')")
    public Report getReport(@PathVariable Long id) {
        // dummy implementation for now
        if (id == 1L) return new Report(1L, 100L, "User 100's Report");
        if (id == 2L) return new Report(2L, 200L, "User 200's Report");
        return new Report(id, 999L, "Someone else's report");
    }
}
