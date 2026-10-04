package com.college.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable=false)
    private String enrollmentNo;
    @Column(nullable=false)
    private String name;
    private String email;
    private String branch;
    private Integer semester;

    public Student(){}
    public Student(String enrollmentNo,String name,String email,String branch,Integer semester){
        this.enrollmentNo=enrollmentNo; this.name=name; this.email=email; this.branch=branch; this.semester=semester;
    }
    public Long getId(){return id;}
    public String getEnrollmentNo(){return enrollmentNo;}
    public void setEnrollmentNo(String v){enrollmentNo=v;}
    public String getName(){return name;}
    public void setName(String v){name=v;}
    public String getEmail(){return email;}
    public void setEmail(String v){email=v;}
    public String getBranch(){return branch;}
    public void setBranch(String v){branch=v;}
    public Integer getSemester(){return semester;}
    public void setSemester(Integer v){semester=v;}
}
