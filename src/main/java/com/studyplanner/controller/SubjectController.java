package com.studyplanner.controller;

import com.studyplanner.dto.SubjectRequest;
import com.studyplanner.entity.Subject;
import com.studyplanner.service.SubjectService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Subject> create(@Valid @RequestBody SubjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public List<Subject> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Subject getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Subject update(@PathVariable Long id, @Valid @RequestBody SubjectRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
