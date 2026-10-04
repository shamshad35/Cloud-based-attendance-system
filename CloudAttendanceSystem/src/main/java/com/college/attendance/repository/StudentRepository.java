package com.college.attendance.repository;
import com.college.attendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface StudentRepository extends JpaRepository<Student,Long>{
    Optional<Student> findByEnrollmentNo(String enrollmentNo);
}
