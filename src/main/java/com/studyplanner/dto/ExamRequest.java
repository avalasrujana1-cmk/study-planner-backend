package com.studyplanner.dto;

import com.studyplanner.entity.ExamType;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ExamRequest(
        @NotBlank(message = "Title is required") @Size(max = 150, message = "Title must be 150 characters or fewer") String title,
        @Size(max = 1000, message = "Description must be 1000 characters or fewer") String description,
        @NotNull(message = "Date is required") LocalDate examDate,
        @NotNull(message = "Type is required") ExamType examType,
        @NotNull(message = "Subject is required") Long subjectId) {
}
