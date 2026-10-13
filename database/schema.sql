-- Smart Study Planner - MySQL schema
-- Run in MySQL Workbench or:  mysql -u root -p < database/schema.sql
-- (The app can also create these tables itself via spring.jpa.hibernate.ddl-auto=update.)

CREATE DATABASE IF NOT EXISTS smart_study_planner
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE smart_study_planner;

CREATE TABLE IF NOT EXISTS subjects (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(500),
  priority VARCHAR(10) NOT NULL,
  target_study_hours DOUBLE NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT chk_subject_priority CHECK (priority IN ('LOW','MEDIUM','HIGH'))
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS study_tasks (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(150) NOT NULL,
  description VARCHAR(1000),
  due_date DATE NOT NULL,
  priority VARCHAR(10) NOT NULL,
  estimated_duration_minutes INT NOT NULL,
  completed BIT NOT NULL DEFAULT 0,
  subject_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_task_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE,
  CONSTRAINT chk_task_priority CHECK (priority IN ('LOW','MEDIUM','HIGH')),
  CONSTRAINT chk_task_duration CHECK (estimated_duration_minutes > 0)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS study_sessions (
  id BIGINT NOT NULL AUTO_INCREMENT,
  topic VARCHAR(150) NOT NULL,
  study_date DATE NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  planned_duration_minutes INT NOT NULL,
  completed_duration_minutes INT NOT NULL DEFAULT 0,
  subject_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_session_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE,
  CONSTRAINT chk_session_times CHECK (end_time > start_time),
  CONSTRAINT chk_session_completed CHECK (completed_duration_minutes >= 0)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS exams (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(150) NOT NULL,
  description VARCHAR(1000),
  exam_date DATE NOT NULL,
  exam_type VARCHAR(20) NOT NULL,
  subject_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_exam_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE,
  CONSTRAINT chk_exam_type CHECK (exam_type IN ('EXAM','QUIZ','ASSIGNMENT','PROJECT'))
) ENGINE=InnoDB;

CREATE INDEX idx_tasks_due ON study_tasks(due_date);
CREATE INDEX idx_sessions_date ON study_sessions(study_date);
CREATE INDEX idx_exams_date ON exams(exam_date);
