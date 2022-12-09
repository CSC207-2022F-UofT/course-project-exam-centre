package usecases.course.updatemembers.responsemodels;

public class UpdateCMemUserResponseModel {

    private final String userId;
    private final String email;
    private final String firstName;
    private final String lastName;

    public UpdateCMemUserResponseModel(String userId,
                                       String email,
                                       String firstName,
                                       String lastName) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

}
