package com.studyplanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "study_sessions")
public class StudySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String topic;

    @Column(nullable = false)
    private LocalDate studyDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Integer plannedDurationMinutes;

    @Column(nullable = false)
    private Integer completedDurationMinutes = 0;

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
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public LocalDate getStudyDate() { return studyDate; }
    public void setStudyDate(LocalDate studyDate) { this.studyDate = studyDate; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public Integer getPlannedDurationMinutes() { return plannedDurationMinutes; }
    public void setPlannedDurationMinutes(Integer m) { this.plannedDurationMinutes = m; }
    public Integer getCompletedDurationMinutes() { return completedDurationMinutes; }
    public void setCompletedDurationMinutes(Integer m) { this.completedDurationMinutes = m; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
}
