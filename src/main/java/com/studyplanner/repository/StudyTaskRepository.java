package com.studyplanner.repository;

import com.studyplanner.entity.StudyTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyTaskRepository extends JpaRepository<StudyTask, Long> {
    long countByCompleted(boolean completed);
    long countBySubject_Id(Long subjectId);
    long countBySubject_IdAndCompleted(Long subjectId, boolean completed);
}
