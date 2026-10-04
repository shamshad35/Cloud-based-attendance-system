package com.college.attendance.config;

import com.college.attendance.model.*;
import com.college.attendance.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(UserRepository users, StudentRepository students, SubjectRepository subjects,
                           AttendanceRepository attendance) {
        return args -> {
            if (users.count()==0) {
                users.save(new User("admin","admin123","FACULTY"));
                users.save(new User("240170107001","student123","STUDENT"));
            }
            if (students.count()==0) {
                students.save(new Student("240170107001","Rahul Patel","rahul@example.com","CE",5));
                students.save(new Student("240170107002","Aman Shah","aman@example.com","CE",5));
                students.save(new Student("240170107003","Priya Mehta","priya@example.com","CE",5));
                students.save(new Student("240170107004","Neha Desai","neha@example.com","CE",5));
                students.save(new Student("240170107005","Arjun Parmar","arjun@example.com","CE",5));
            }
            if (subjects.count()==0) {
                subjects.save(new Subject("CC501","Cloud Computing"));
                subjects.save(new Subject("CN502","Computer Networks"));
                subjects.save(new Subject("DB503","Database Management"));
                subjects.save(new Subject("SS504","System Software"));
            }
            if (attendance.count()==0) {
                Student s1=students.findByEnrollmentNo("240170107001").orElseThrow();
                Student s2=students.findByEnrollmentNo("240170107002").orElseThrow();
                Subject cc=subjects.findAll().get(0);
                Subject cn=subjects.findAll().get(1);
                attendance.save(new Attendance(s1,cc,LocalDate.now().minusDays(2),true));
                attendance.save(new Attendance(s1,cc,LocalDate.now().minusDays(1),true));
                attendance.save(new Attendance(s1,cc,LocalDate.now(),false));
                attendance.save(new Attendance(s1,cn,LocalDate.now().minusDays(2),true));
                attendance.save(new Attendance(s1,cn,LocalDate.now().minusDays(1),false));
                attendance.save(new Attendance(s2,cc,LocalDate.now().minusDays(2),true));
            }
        };
    }
}
