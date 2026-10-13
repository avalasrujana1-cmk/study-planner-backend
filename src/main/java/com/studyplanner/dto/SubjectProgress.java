package com.studyplanner.dto;

public record SubjectProgress(
        Long subjectId,
        String subjectName,
        double targetStudyHours,
        long totalTasks,
        long completedTasks,
        double completionPercentage,
        double plannedStudyHours,
        double completedStudyHours,
        double targetCompletionPercentage) {
}
