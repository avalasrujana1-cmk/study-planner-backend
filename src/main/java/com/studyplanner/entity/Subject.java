package com.studyplanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Priority priority = Priority.MEDIUM;

    @Column(nullable = false)
    private Double targetStudyHours = 0.0;

    // One subject -> many tasks / sessions / exams. Deleting a subject deletes its children.
    @JsonIgnore
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyTask> tasks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudySession> sessions = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> exams = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public Double getTargetStudyHours() { return targetStudyHours; }
    public void setTargetStudyHours(Double targetStudyHours) { this.targetStudyHours = targetStudyHours; }
    public List<StudyTask> getTasks() { return tasks; }
    public List<StudySession> getSessions() { return sessions; }
    public List<Exam> getExams() { return exams; }
}
