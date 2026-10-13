package com.studyplanner.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

public record SessionRequest(
        @NotBlank(message = "Topic is required") @Size(max = 150, message = "Topic must be 150 characters or fewer") String topic,
        @NotNull(message = "Study date is required") LocalDate studyDate,
        @NotNull(message = "Start time is required") LocalTime startTime,
        @NotNull(message = "End time is required") LocalTime endTime,
        @Min(value = 0, message = "Completed minutes cannot be negative")
        @Max(value = 1440, message = "Completed minutes cannot exceed 1440") Integer completedDurationMinutes,
        @NotNull(message = "Subject is required") Long subjectId) {
}
