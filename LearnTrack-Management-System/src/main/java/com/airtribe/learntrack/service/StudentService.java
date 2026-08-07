package com.airtribe.learntrack.service;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;

import java.util.List;

import static com.airtribe.learntrack.util.IdGenerator.generateStudentId;


public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(String firstName, String lastName, String email, int batch) {

        if (batch < AppConstants.MIN_BATCH_YEAR || batch > AppConstants.MAX_BATCH_YEAR) {
            throw new InvalidInputException(
                    "Joining year must be between 2020 and 2035."
            );
        }
        String id = generateStudentId();
        studentRepository.addStudent(new Student(id, firstName, lastName, email, batch, true));


    }

    public List<Student> viewAllStudents() {
        return studentRepository.viewAllStudents();
    }

    public Student searchStudentById(String studentId) {

        Student student = studentRepository.searchStudentById(studentId);
        if (student == null) {
            throw new EntityNotFoundException(
                    AppConstants.STUDENT_NOT_FOUND + studentId
            );
        }

        return student;
    }

    public boolean deactivateStudent(String studentId) {
        Student student = studentRepository.searchStudentById(studentId);

        if (student == null) {
            throw new EntityNotFoundException(
                    AppConstants.STUDENT_NOT_FOUND + studentId
            );
        }

        if (student.isActive()) {
            student.setActive(false);
            return true;
        }

        return false;

    }
}
