package usecases.course.updatemembers;

import java.util.List;

public interface UpdateCMemDsGateway
{
    boolean checkIfCourseExists(String courseId);
    void addCourseEnrollment(String courseId, String userId);
    void removeCourseEnrollment(String courseId, String userId);
    List<String> getCourseEnrollment(String userId);

}
