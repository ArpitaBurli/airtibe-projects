package com.airtribe.learntrack.service;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    public Enrollment enrollStudent(String studentId, String courseId) {

        Student student = studentRepository.searchStudentById(studentId);

        if (student == null) {
            throw new EntityNotFoundException(
                    AppConstants.STUDENT_NOT_FOUND + studentId
            );
        }

        if (!student.isActive()) {
            throw new InvalidInputException(
                    "Student is inactive. Please activate the student before enrolling."
            );
        }


        Course course = courseRepository.findCourseById(courseId);

        if (course == null) {
            throw new EntityNotFoundException(
                    AppConstants.COURSE_NOT_FOUND + courseId
            );
        }

        if (!course.isActive()) {
            throw new InvalidInputException(
                    "Course is inactive. Please activate the course before enrolling."
            );
        }
        Enrollment existingEnrollment =
                enrollmentRepository.findEnrollmentByStudentAndCourse(studentId, courseId);

        if (existingEnrollment != null) {
            throw new InvalidInputException(
                    "Student is already enrolled in this course."
            );
        }

        String id = IdGenerator.generateEnrollmentId();

        Enrollment enrollment = new Enrollment(
                id,
                student,
                course,
                EnrollmentStatus.ACTIVE
        );

        enrollmentRepository.addEnrollment(enrollment);

        return enrollment;
    }

    public List<Enrollment> viewEnrollmentsByStudentId(String studentId) {

        Student student = studentRepository.searchStudentById(studentId);

        if (student == null) {
            throw new EntityNotFoundException(
                    AppConstants.STUDENT_NOT_FOUND + studentId
            );
        }

        List<Enrollment> enrollments =
                enrollmentRepository.findEnrollmentsByStudentId(studentId);

        if (enrollments.isEmpty()) {
            throw new EntityNotFoundException(
                    "No enrollments found for student: " + studentId
            );
        }

        return enrollments;
    }

    public Enrollment findEnrollmentById(String id) {
        Enrollment enrollment = enrollmentRepository.findEnrollmentById(id);

        if (enrollment == null) {
            throw new EntityNotFoundException(
                    AppConstants.ENROLLMENT_NOT_FOUND + id
            );
        }

        return enrollment;

    }


    public void updateEnrollmentStatus(String id, EnrollmentStatus status) {

        Enrollment enrollment = findEnrollmentById(id);
        enrollment.setStatus(status);

    }

}
