package uc.course.register;

public interface CRegisterDsGateway {
    boolean checkIfCourseExists(String identifier);
    void saveCourseInfo(CRegisterDsRequestModel requestModel);
}
