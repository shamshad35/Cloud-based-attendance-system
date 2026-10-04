# Cloud-Based Student Attendance System

A college mini project using Java Spring Boot, MySQL, HTML/CSS and REST-style server architecture.

## Requirements
- JDK 17+
- Maven 3.9+ (or use Maven Wrapper if added)
- MySQL 8+
- VS Code / IntelliJ / Eclipse
- Internet connection for cloud deployment

## 1. Create database
The application can create the database automatically if the MySQL server is running. If preferred:
```sql
CREATE DATABASE attendance_db;
```

## 2. Configure MySQL
Default configuration:
- Host: localhost
- Port: 3306
- Database: attendance_db
- Username: root
- Password: root

If your MySQL password is different, edit `src/main/resources/application.properties`.

## 3. Run
Open the project folder in VS Code terminal:
```bash
mvn spring-boot:run
```
or:
```bash
mvn clean package
java -jar target/cloud-attendance-system-1.0.0.jar
```

Open:
http://localhost:8080

## Demo login
Faculty:
- Username: admin
- Password: admin123

Student:
- Username: 240170107001
- Password: student123

## Features
- Faculty authentication
- Student authentication
- Faculty attendance marking
- Present/Absent status
- Attendance percentage
- Attendance history
- MySQL persistence
- Cloud-ready environment variables
- Responsive web interface

## Cloud deployment
Set these environment variables on your hosting platform:
DB_URL=jdbc:mysql://YOUR_DB_HOST:3306/attendance_db?useSSL=true&serverTimezone=UTC
DB_USERNAME=YOUR_DB_USER
DB_PASSWORD=YOUR_DB_PASSWORD
PORT=8080

Build command:
mvn clean package -DskipTests

Start command:
java -jar target/cloud-attendance-system-1.0.0.jar

For AWS, a common college-demo architecture is:
Browser -> HTTPS -> EC2/Spring Boot -> RDS MySQL

## Important security note
The demo uses simple passwords to keep the college project easy to understand. For a production deployment, use password hashing (BCrypt), HTTPS, CSRF protection, role-based authorization, secrets management, and environment variables.
