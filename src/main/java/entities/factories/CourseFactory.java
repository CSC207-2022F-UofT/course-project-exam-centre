package entities.factories;

import entities.Course;

public class CourseFactory {
    /** Constructs a new course
     *
     * @param courseName the name of the course
     * @param courseCode the course code
     * @param courseId the unique id representing the course
     * @return returns a new course object
     */
    public Course create(String courseName, String courseCode, String courseId) {
        return new Course(courseName, courseCode, courseId);
    }
}
