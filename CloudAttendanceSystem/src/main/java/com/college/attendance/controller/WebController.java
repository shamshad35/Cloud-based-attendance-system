package com.college.attendance.controller;

import com.college.attendance.model.*;
import com.college.attendance.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@Controller
public class WebController {
    private final UserRepository users;
    private final StudentRepository students;
    private final SubjectRepository subjects;
    private final AttendanceRepository attendance;

    public WebController(UserRepository u, StudentRepository s, SubjectRepository sub, AttendanceRepository a){
        users=u;students=s;subjects=sub;attendance=a;
    }

    @GetMapping("/")
    public String home(){return "redirect:/login";}

    @GetMapping("/login")
    public String login(){return "login";}

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,@RequestParam String password,
                          HttpSession session, Model model){
        Optional<User> user=users.findByUsername(username);
        if(user.isPresent() && user.get().getPassword().equals(password)){
            session.setAttribute("user",user.get());
            if("FACULTY".equals(user.get().getRole())) return "redirect:/faculty";
            return "redirect:/student";
        }
        model.addAttribute("error","Invalid username or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){session.invalidate();return "redirect:/login";}

    @GetMapping("/faculty")
    public String faculty(Model model,HttpSession session){
        User u=(User)session.getAttribute("user");
        if(u==null || !"FACULTY".equals(u.getRole())) return "redirect:/login";
        model.addAttribute("students",students.findAll());
        model.addAttribute("subjects",subjects.findAll());
        model.addAttribute("today",LocalDate.now());
        return "faculty";
    }

    @PostMapping("/faculty/attendance")
    public String saveAttendance(@RequestParam Long subjectId,@RequestParam String date,
                                 @RequestParam Map<String,String> params,HttpSession session){
        User u=(User)session.getAttribute("user");
        if(u==null || !"FACULTY".equals(u.getRole())) return "redirect:/login";
        Subject sub=subjects.findById(subjectId).orElseThrow();
        LocalDate d=LocalDate.parse(date);
        for(Student s:students.findAll()){
            String value=params.get("student_"+s.getId());
            if(value==null) continue;
            boolean present="present".equals(value);
            List<Attendance> existing=attendance.findBySubjectIdAndDate(subjectId,d);
            Attendance record=existing.stream().filter(x->x.getStudent().getId().equals(s.getId())).findFirst().orElse(null);
            if(record==null) attendance.save(new Attendance(s,sub,d,present));
            else {record.setPresent(present); attendance.save(record);}
        }
        return "redirect:/faculty?success=Attendance+saved";
    }

    @GetMapping("/student")
    public String student(Model model,HttpSession session){
        User u=(User)session.getAttribute("user");
        if(u==null || !"STUDENT".equals(u.getRole())) return "redirect:/login";
        Student s=students.findByEnrollmentNo(u.getUsername()).orElse(null);
        if(s==null) return "redirect:/logout";
        List<Map<String,Object>> summary=new ArrayList<>();
        for(Subject sub:subjects.findAll()){
            long total=attendance.countByStudentIdAndSubjectId(s.getId(),sub.getId());
            long present=attendance.countByStudentIdAndSubjectIdAndPresentTrue(s.getId(),sub.getId());
            double pct=total==0?0:(present*100.0/total);
            Map<String,Object> m=new HashMap<>();
            m.put("subject",sub);m.put("total",total);m.put("present",present);m.put("percentage",String.format("%.2f",pct));
            summary.add(m);
        }
        model.addAttribute("student",s);
        model.addAttribute("summary",summary);
        model.addAttribute("records",attendance.findByStudentIdOrderByDateDesc(s.getId()));
        return "student";
    }
}
