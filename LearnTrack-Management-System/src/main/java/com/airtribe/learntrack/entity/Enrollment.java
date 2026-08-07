package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;

public class Enrollment {
    private String id;
    private Student student;
    private Course course;
    private EnrollmentStatus status;

    public Enrollment(String id, Student student, Course course, EnrollmentStatus status) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nEnrollment ID : " + id +
                "\nStudent ID    : " + student.getId() +
                "\nStudent Name  : " + student.getFirstName() + " " + student.getLastName() +
                "\nCourse ID     : " + course.getId() +
                "\nCourse Name   : " + course.getCourseName() +
                "\nStatus        : " + status;
    }
}
