package com.studyplanner.dto;

public record ProgressResponse(
        long totalTasks,
        long completedTasks,
        long pendingTasks,
        double completionPercentage,
        double plannedStudyHours,
        double completedStudyHours,
        double hoursCompletionPercentage) {
}
