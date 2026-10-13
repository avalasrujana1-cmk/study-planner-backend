package com.studyplanner.controller;

import com.studyplanner.dto.SessionRequest;
import com.studyplanner.entity.StudySession;
import com.studyplanner.service.StudySessionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/study-sessions")
public class StudySessionController {

    private final StudySessionService service;

    public StudySessionController(StudySessionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudySession> create(@Valid @RequestBody SessionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public List<StudySession> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StudySession getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public StudySession update(@PathVariable Long id, @Valid @RequestBody SessionRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
