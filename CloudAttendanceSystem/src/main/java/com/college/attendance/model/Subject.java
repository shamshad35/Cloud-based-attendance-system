package com.college.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name="subjects")
public class Subject {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String code;
    @Column(nullable=false)
    private String name;

    public Subject(){}
    public Subject(String code,String name){this.code=code;this.name=name;}
    public Long getId(){return id;}
    public String getCode(){return code;}
    public void setCode(String v){code=v;}
    public String getName(){return name;}
    public void setName(String v){name=v;}
}
