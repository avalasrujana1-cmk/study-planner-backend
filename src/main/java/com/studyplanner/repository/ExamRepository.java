package com.studyplanner.repository;

import com.studyplanner.entity.Exam;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByExamDateGreaterThanEqualOrderByExamDateAsc(LocalDate date);
}
