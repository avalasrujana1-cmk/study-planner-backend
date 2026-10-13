package com.studyplanner.service;

import com.studyplanner.dto.TaskRequest;
import com.studyplanner.entity.StudyTask;
import com.studyplanner.exception.ResourceNotFoundException;
import com.studyplanner.repository.StudyTaskRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {

    private final StudyTaskRepository repo;
    private final SubjectService subjects;

    public TaskService(StudyTaskRepository repo, SubjectService subjects) {
        this.repo = repo;
        this.subjects = subjects;
    }

    @Transactional(readOnly = true)
    public List<StudyTask> findAll() {
        return repo.findAll(Sort.by("dueDate").ascending().and(Sort.by("id")));
    }

    @Transactional(readOnly = true)
    public StudyTask findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", id));
    }

    public StudyTask create(TaskRequest r) {
        StudyTask t = new StudyTask();
        apply(t, r);
        return repo.save(t);
    }

    public StudyTask update(Long id, TaskRequest r) {
        StudyTask t = findById(id);
        apply(t, r);
        return repo.save(t);
    }

    public StudyTask setCompleted(Long id, boolean completed) {
        StudyTask t = findById(id);
        t.setCompleted(completed);
        return repo.save(t);
    }

    public void delete(Long id) {
        repo.delete(findById(id));
    }

    private void apply(StudyTask t, TaskRequest r) {
        t.setTitle(r.title().trim());
        t.setDescription(r.description());
        t.setDueDate(r.dueDate());
        t.setPriority(r.priority());
        t.setEstimatedDurationMinutes(r.estimatedDurationMinutes());
        t.setCompleted(Boolean.TRUE.equals(r.completed()));
        t.setSubject(subjects.findById(r.subjectId()));
    }
}
