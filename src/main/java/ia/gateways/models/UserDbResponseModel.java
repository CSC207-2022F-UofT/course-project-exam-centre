package ia.gateways.models;

import uc.course.updatemembers.dbmodels.UpdateCMemUserDbModel;
import uc.dboard.submessage.dbmodels.SubDBMessUserDbModel;
import uc.state.update.dbmodels.UpdateStateUserDbModel;
import uc.user.login.LoginDsResponseModel;

public class UserDbResponseModel
        implements UpdateStateUserDbModel,
        LoginDsResponseModel,
        UpdateCMemUserDbModel,
        SubDBMessUserDbModel {

    private final String userId;
    private final String email;
    private final String firstName;
    private final String lastName;

    public UserDbResponseModel(String userId,
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
