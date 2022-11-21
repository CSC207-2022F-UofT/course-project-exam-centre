package uc.user.register;

public interface URegisterDsGateway {
    boolean checkIfUserExistsByEmail(String email);
    boolean saveNewUserInfo(URegisterDsRequestModel requestModel);
    boolean checkIfUserExists(String userId);

}
