package com.studyplanner.service;

import com.studyplanner.dto.ExamRequest;
import com.studyplanner.entity.Exam;
import com.studyplanner.exception.ResourceNotFoundException;
import com.studyplanner.repository.ExamRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExamService {

    private final ExamRepository repo;
    private final SubjectService subjects;

    public ExamService(ExamRepository repo, SubjectService subjects) {
        this.repo = repo;
        this.subjects = subjects;
    }

    @Transactional(readOnly = true)
    public List<Exam> findAll() {
        return repo.findAll(Sort.by("examDate").ascending().and(Sort.by("id")));
    }

    @Transactional(readOnly = true)
    public Exam findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exam", id));
    }

    public Exam create(ExamRequest r) {
        Exam e = new Exam();
        apply(e, r);
        return repo.save(e);
    }

    public Exam update(Long id, ExamRequest r) {
        Exam e = findById(id);
        apply(e, r);
        return repo.save(e);
    }

    public void delete(Long id) {
        repo.delete(findById(id));
    }

    private void apply(Exam e, ExamRequest r) {
        e.setTitle(r.title().trim());
        e.setDescription(r.description());
        e.setExamDate(r.examDate());
        e.setExamType(r.examType());
        e.setSubject(subjects.findById(r.subjectId()));
    }
}
