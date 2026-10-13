package com.studyplanner.dto;

import com.studyplanner.entity.Priority;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record TaskRequest(
        @NotBlank(message = "Title is required") @Size(max = 150, message = "Title must be 150 characters or fewer") String title,
        @Size(max = 1000, message = "Description must be 1000 characters or fewer") String description,
        @NotNull(message = "Due date is required") LocalDate dueDate,
        @NotNull(message = "Priority is required") Priority priority,
        @NotNull(message = "Estimated duration is required")
        @Min(value = 1, message = "Duration must be at least 1 minute")
        @Max(value = 1440, message = "Duration cannot exceed 1440 minutes") Integer estimatedDurationMinutes,
        Boolean completed,
        @NotNull(message = "Subject is required") Long subjectId) {
}
