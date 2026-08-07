package com.airtribe.learntrack.service;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;


    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String courseName, int durationInWeeks, String description) {

        Course existingCourse = courseRepository.findCourseByName(courseName);

        if (existingCourse != null) {
            throw new InvalidInputException(
                    "Course already exists with name: " + courseName
            );
        }

        String id = IdGenerator.generateCourseId();

        Course course = new Course(
                id,
                courseName,
                description,
                durationInWeeks,
                true
        );

        courseRepository.addCourse(course);

        return course;
    }

    public List<Course> viewAllCourses() {
        return courseRepository.viewAllCourses();
    }

    public void updateCourseStatus(String courseId, boolean active) {

        Course course = courseRepository.findCourseById(courseId);

        if (course == null) {
            throw new EntityNotFoundException(
                    AppConstants.COURSE_NOT_FOUND + courseId
            );
        }

        if (course.isActive() == active) {

            if (active) {
                System.out.println("Course is already active.");
            } else {
                System.out.println("Course is already inactive.");
            }

            return;
        }

        course.setActive(active);

        if (active) {
            System.out.println("Course activated successfully.");
        } else {
            System.out.println("Course deactivated successfully.");
        }
    }
}



