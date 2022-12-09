package iadapters.gateways.models;

import usecases.course.updatemembers.dbmodels.UpdateCMemUserDbModel;
import usecases.submessage.dbmodels.SubDBMessUserDbModel;
import usecases.doc.submitsolution.dbmodels.SubmitSDocUserDbModel;
import usecases.state.update.dbmodels.UpdateStateUserDbModel;
import usecases.user.login.LoginDsResponseModel;

public class UserDbResponseModel
        implements UpdateStateUserDbModel,
        LoginDsResponseModel,
        UpdateCMemUserDbModel,
        SubDBMessUserDbModel,
        SubmitSDocUserDbModel {

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
