package com.college.attendance.repository;
import com.college.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface AttendanceRepository extends JpaRepository<Attendance,Long>{
    List<Attendance> findByStudentIdOrderByDateDesc(Long studentId);
    List<Attendance> findBySubjectIdAndDate(Long subjectId, java.time.LocalDate date);
    long countByStudentIdAndSubjectId(Long studentId, Long subjectId);
    long countByStudentIdAndSubjectIdAndPresentTrue(Long studentId, Long subjectId);
}
