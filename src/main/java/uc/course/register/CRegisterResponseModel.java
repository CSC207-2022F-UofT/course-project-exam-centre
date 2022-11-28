package uc.course.register;

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
