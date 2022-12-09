package uc.course.register;

/** CRegisterDsGateway provides methods to compare information and save to persistent data.
 * Takes information in the form of CRegisterDsRequestModel
 * @layer use cases
 */
public interface CRegisterDsGateway {
    /**
     * Method to check if the course exists within persistent data.
     * @param identifier the name of the course
     * @return if the course exists within persistent data
     */
    boolean checkIfCourseExists(String identifier);

    /**
     * Method to save the course's information to persistent data.
     * @param requestModel The information of the course being saved
     * @return the course's unique id
     */
    String saveCourse(CRegisterDsRequestModel requestModel);
    
    /** Checks whether gateway is connected to database.
     *
     * @return boolean representing whether database is connected
     */
    boolean getConnectionStatus();
}
