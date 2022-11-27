package entities;

import java.util.List;

public interface User {

    String getUserId();

    String getName();

    String getFirstName();

    String getLastName();

    String getEmail();

    List<String> getTests();

    List<String> getSolutions();

    List<String> getCourses();

    Boolean addCourse(String newCourse);

    Boolean removeCourse(String courseCode);

    Boolean addTest(String newTestId);

    Boolean addSolution(String newSolutionId);
}
