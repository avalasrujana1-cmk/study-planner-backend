package com.studyplanner.service;

import com.studyplanner.dto.DashboardSummary;
import com.studyplanner.dto.ProgressResponse;
import com.studyplanner.dto.SubjectProgress;
import com.studyplanner.entity.Subject;
import com.studyplanner.repository.ExamRepository;
import com.studyplanner.repository.StudySessionRepository;
import com.studyplanner.repository.StudyTaskRepository;
import com.studyplanner.repository.SubjectRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Calculates dashboard and progress statistics from the real database rows. */
@Service
@Transactional(readOnly = true)
public class DashboardService {

    private final SubjectRepository subjects;
    private final StudyTaskRepository tasks;
    private final StudySessionRepository sessions;
    private final ExamRepository exams;

    public DashboardService(SubjectRepository subjects, StudyTaskRepository tasks,
                            StudySessionRepository sessions, ExamRepository exams) {
        this.subjects = subjects;
        this.tasks = tasks;
        this.sessions = sessions;
        this.exams = exams;
    }

    public DashboardSummary summary() {
        ProgressResponse p = progress();
        LocalDate today = LocalDate.now();
        return new DashboardSummary(
                subjects.count(), p.totalTasks(), p.completedTasks(), p.pendingTasks(),
                p.completionPercentage(), p.plannedStudyHours(), p.completedStudyHours(),
                sessions.findByStudyDateOrderByStartTimeAsc(today),
                exams.findByExamDateGreaterThanEqualOrderByExamDateAsc(today).stream().limit(5).toList());
    }

    public ProgressResponse progress() {
        long total = tasks.count();
        long done = tasks.countByCompleted(true);
        double planned = hours(sessions.sumPlannedMinutes());
        double completed = hours(sessions.sumCompletedMinutes());
        return new ProgressResponse(total, done, total - done, percent(done, total),
                planned, completed, percent(completed, planned));
    }

    public List<SubjectProgress> subjectProgress() {
        return subjects.findAll(Sort.by("name")).stream().map(this::forSubject).toList();
    }

    private SubjectProgress forSubject(Subject s) {
        long total = tasks.countBySubjectId(s.getId());
        long done = tasks.countBySubjectIdAndCompleted(s.getId(), true);
        double planned = hours(sessions.sumPlannedMinutesBySubject(s.getId()));
        double completed = hours(sessions.sumCompletedMinutesBySubject(s.getId()));
        return new SubjectProgress(s.getId(), s.getName(), s.getTargetStudyHours(), total, done,
                percent(done, total), planned, completed, percent(completed, s.getTargetStudyHours()));
    }

    private static double hours(long minutes) {
        return Math.round(minutes / 60.0 * 100.0) / 100.0;
    }

    private static double percent(double part, double whole) {
        if (whole <= 0) return 0;
        return Math.min(100.0, Math.round(part / whole * 1000.0) / 10.0);
    }
}
