package com.college.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private String role;

    public User() {}
    public User(String username, String password, String role) {
        this.username=username; this.password=password; this.role=role;
    }
    public Long getId(){return id;}
    public String getUsername(){return username;}
    public void setUsername(String v){username=v;}
    public String getPassword(){return password;}
    public void setPassword(String v){password=v;}
    public String getRole(){return role;}
    public void setRole(String v){role=v;}
}
