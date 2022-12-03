package uc.course.register;

/** CRegisterDsGateway provides methods to compare information and save to persistent data.
 * Takes information in the form of CRegisterDsRequestModel
 * @layer use cases
 */
public interface CRegisterDsGateway {
    boolean checkIfCourseExists(String identifier);
    String saveCourse(CRegisterDsRequestModel requestModel);
}
