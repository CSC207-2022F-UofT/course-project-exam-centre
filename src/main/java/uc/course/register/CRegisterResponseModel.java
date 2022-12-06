package uc.course.register;

/** The CRegisterResponseModel is responsible for formatting information in a way the presenter can use.
 * @layer use cases
 */
public class CRegisterResponseModel {
    private String course;
    private boolean registrationStatus;
    private String timestamp;

    /** Creates an instance of the CRegisterResponseModel containing the course and registration status
     *
     * @param course the String of the course corresponding to the course being made
     * @param registrationStatus the status of the registration
     * @param timestamp how long it takes the registration to happen
     */
    public CRegisterResponseModel(String course, boolean registrationStatus, String timestamp) {
        this.course = course;
        this.registrationStatus = registrationStatus;
        this.timestamp = timestamp;
    }
}
