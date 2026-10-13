package com.studyplanner.dto;

import com.studyplanner.entity.Priority;
import jakarta.validation.constraints.*;

public record SubjectRequest(
        @NotBlank(message = "Subject name is required") @Size(max = 100, message = "Name must be 100 characters or fewer") String name,
        @Size(max = 500, message = "Description must be 500 characters or fewer") String description,
        @NotNull(message = "Priority is required") Priority priority,
        @NotNull(message = "Target study hours is required")
        @DecimalMin(value = "0.0", message = "Target study hours cannot be negative")
        @DecimalMax(value = "1000.0", message = "Target study hours is too large") Double targetStudyHours) {
}
