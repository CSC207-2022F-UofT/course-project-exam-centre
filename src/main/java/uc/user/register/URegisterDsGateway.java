package uc.user.register;

public interface URegisterDsGateway {
    boolean checkIfAccountExists(String email);

    boolean saveUserInfo(URegisterDsRequestModel requestModel);
    boolean checkIfIdExists(String userId);

}
