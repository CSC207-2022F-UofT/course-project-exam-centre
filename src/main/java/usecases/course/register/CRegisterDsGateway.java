package usecases.course.register;

public interface CRegisterDsGateway {
    boolean checkIfCourseExists(String identifier);
    void saveCourseInfo(CRegisterDsRequestModel requestModel);
}
