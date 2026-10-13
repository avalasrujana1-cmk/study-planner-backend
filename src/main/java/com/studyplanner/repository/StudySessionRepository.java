package com.studyplanner.repository;

import com.studyplanner.entity.StudySession;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {

    List<StudySession> findByStudyDateOrderByStartTimeAsc(LocalDate date);

    @Query("select coalesce(sum(s.plannedDurationMinutes), 0) from StudySession s")
    long sumPlannedMinutes();

    @Query("select coalesce(sum(s.completedDurationMinutes), 0) from StudySession s")
    long sumCompletedMinutes();

    @Query("select coalesce(sum(s.plannedDurationMinutes), 0) from StudySession s where s.subject.id = :subjectId")
    long sumPlannedMinutesBySubject(@Param("subjectId") Long subjectId);

    @Query("select coalesce(sum(s.completedDurationMinutes), 0) from StudySession s where s.subject.id = :subjectId")
    long sumCompletedMinutesBySubject(@Param("subjectId") Long subjectId);
}
