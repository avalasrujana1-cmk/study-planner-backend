package com.studyplanner.controller;

import com.studyplanner.dto.DashboardSummary;
import com.studyplanner.dto.ProgressResponse;
import com.studyplanner.dto.SubjectProgress;
import com.studyplanner.service.DashboardService;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/dashboard/summary")
    public DashboardSummary summary() {
        return service.summary();
    }

    @GetMapping("/progress")
    public ProgressResponse progress() {
        return service.progress();
    }

    @GetMapping("/progress/subjects")
    public List<SubjectProgress> subjectProgress() {
        return service.subjectProgress();
    }

    /** Simple check used by the Settings page to test the connection. */
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}
