package com.studyplanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDate examDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ExamType examType = ExamType.EXAM;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @JsonProperty("subjectId")
    public Long getSubjectId() { return subject != null ? subject.getId() : null; }

    @JsonProperty("subjectName")
    public String getSubjectName() { return subject != null ? subject.getName() : null; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getExamDate() { return examDate; }
    public void setExamDate(LocalDate examDate) { this.examDate = examDate; }
    public ExamType getExamType() { return examType; }
    public void setExamType(ExamType examType) { this.examType = examType; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
}
