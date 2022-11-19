package entities;

import java.util.List;

public interface User {

    int getUserId();

    String getName();

    String getEmail();

    List<Integer> getTests();

    List<Integer> getSolutions();

    List<String> getCourses();

    Boolean addCourse(String newCourse);

    Boolean removeCourse(String courseCode);

    Boolean addTest(Integer newTestId);

    Boolean addSolution(Integer newSolutionId);
}
