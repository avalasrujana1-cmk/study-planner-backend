package com.studyplanner.service;

import com.studyplanner.dto.SubjectRequest;
import com.studyplanner.entity.Subject;
import com.studyplanner.exception.ResourceNotFoundException;
import com.studyplanner.repository.SubjectRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectService {

    private final SubjectRepository repo;

    public SubjectService(SubjectRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        return repo.findAll(Sort.by("name"));
    }

    @Transactional(readOnly = true)
    public Subject findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject", id));
    }

    public Subject create(SubjectRequest r) {
        Subject s = new Subject();
        apply(s, r);
        return repo.save(s);
    }

    public Subject update(Long id, SubjectRequest r) {
        Subject s = findById(id);
        apply(s, r);
        return repo.save(s);
    }

    public void delete(Long id) {
        repo.delete(findById(id));
    }

    private void apply(Subject s, SubjectRequest r) {
        s.setName(r.name().trim());
        s.setDescription(r.description());
        s.setPriority(r.priority());
        s.setTargetStudyHours(r.targetStudyHours());
    }
}
