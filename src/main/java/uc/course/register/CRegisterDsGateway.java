package uc.course.register;

public interface CRegisterDsGateway {
    boolean checkIfCourseExists(String identifier);
    String saveCourse(CRegisterDsRequestModel requestModel);
    boolean getConnectionStatus();
}
