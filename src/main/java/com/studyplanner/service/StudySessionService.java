package com.studyplanner.service;

import com.studyplanner.dto.SessionRequest;
import com.studyplanner.entity.StudySession;
import com.studyplanner.exception.BadRequestException;
import com.studyplanner.exception.ResourceNotFoundException;
import com.studyplanner.repository.StudySessionRepository;
import java.time.Duration;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudySessionService {

    private final StudySessionRepository repo;
    private final SubjectService subjects;

    public StudySessionService(StudySessionRepository repo, SubjectService subjects) {
        this.repo = repo;
        this.subjects = subjects;
    }

    @Transactional(readOnly = true)
    public List<StudySession> findAll() {
        return repo.findAll(Sort.by("studyDate").ascending().and(Sort.by("startTime")));
    }

    @Transactional(readOnly = true)
    public StudySession findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Study session", id));
    }

    public StudySession create(SessionRequest r) {
        StudySession s = new StudySession();
        apply(s, r);
        return repo.save(s);
    }

    public StudySession update(Long id, SessionRequest r) {
        StudySession s = findById(id);
        apply(s, r);
        return repo.save(s);
    }

    public void delete(Long id) {
        repo.delete(findById(id));
    }

    private void apply(StudySession s, SessionRequest r) {
        if (!r.endTime().isAfter(r.startTime())) {
            throw new BadRequestException("End time must be after start time");
        }
        int planned = (int) Duration.between(r.startTime(), r.endTime()).toMinutes();
        int completed = r.completedDurationMinutes() == null ? 0 : r.completedDurationMinutes();
        s.setTopic(r.topic().trim());
        s.setStudyDate(r.studyDate());
        s.setStartTime(r.startTime());
        s.setEndTime(r.endTime());
        s.setPlannedDurationMinutes(planned);   // calculated from start/end time
        s.setCompletedDurationMinutes(completed);
        s.setSubject(subjects.findById(r.subjectId()));
    }
}
