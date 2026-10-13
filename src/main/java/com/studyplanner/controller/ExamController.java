package com.studyplanner.controller;

import com.studyplanner.dto.ExamRequest;
import com.studyplanner.entity.Exam;
import com.studyplanner.service.ExamService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService service;

    public ExamController(ExamService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Exam> create(@Valid @RequestBody ExamRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public List<Exam> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Exam getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Exam update(@PathVariable Long id, @Valid @RequestBody ExamRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
