package com.studyplanner.controller;

import com.studyplanner.dto.TaskRequest;
import com.studyplanner.entity.StudyTask;
import com.studyplanner.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudyTask> create(@Valid @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public List<StudyTask> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StudyTask getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public StudyTask update(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        return service.update(id, request);
    }

    @PatchMapping("/{id}/complete")
    public StudyTask complete(@PathVariable Long id) {
        return service.setCompleted(id, true);
    }

    /** Extra endpoint so a completed task can be moved back to pending. */
    @PatchMapping("/{id}/pending")
    public StudyTask pending(@PathVariable Long id) {
        return service.setCompleted(id, false);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
