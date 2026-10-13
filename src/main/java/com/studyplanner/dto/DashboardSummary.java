package com.studyplanner.dto;

import com.studyplanner.entity.Exam;
import com.studyplanner.entity.StudySession;
import java.util.List;

public record DashboardSummary(
        long totalSubjects,
        long totalTasks,
        long completedTasks,
        long pendingTasks,
        double completionPercentage,
        double plannedStudyHours,
        double completedStudyHours,
        List<StudySession> todaySessions,
        List<Exam> upcomingExams) {
}
