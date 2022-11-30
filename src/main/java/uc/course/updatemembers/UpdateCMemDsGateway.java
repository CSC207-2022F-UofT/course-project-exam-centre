package uc.course.updatemembers;

import java.util.List;

public interface UpdateCMemDsGateway
{
    boolean checkIfCourseExists(String courseId);
    void addCourseEnrolment(String courseId, String userId);
    void removeCourseEnrolment(String courseId, String userId);
    List<String> getCourseIdsByUserId(String userId);
}
