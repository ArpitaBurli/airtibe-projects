package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;

import java.util.ArrayList;
import java.util.List;


public class EnrollmentRepository {

    private final List<Enrollment> enrollmentList = new ArrayList<>();


    public void addEnrollment(Enrollment enrollment) {
        enrollmentList.add(enrollment);

    }

    public Enrollment findEnrollmentById(String id) {
        return enrollmentList.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Enrollment> findEnrollmentsByStudentId(String studentId) {

        return enrollmentList.stream()
                .filter(i -> i.getStudent().getId().equals(studentId))
                .toList();
    }

    public Enrollment findEnrollmentByStudentAndCourse(String studentId, String courseId) {

        return enrollmentList.stream()
                .filter(enrollment ->
                        enrollment.getStudent().getId().equals(studentId)
                                && enrollment.getCourse().getId().equals(courseId))
                .findFirst()
                .orElse(null);
    }

}



