package uc.course.register;

/** The CRegisterResponseModel is responsible for formatting information in a way the presenter can use.
 * @layer use cases
 */
public class CRegisterResponseModel {
    private String course;
    private boolean registrationStatus;
    private String timestamp;

    public CRegisterResponseModel(String course, boolean registrationStatus, String timestamp) {
        this.course = course;
        this.registrationStatus = registrationStatus;
        this.timestamp = timestamp;
    }
}
