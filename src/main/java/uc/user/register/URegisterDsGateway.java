package uc.user.register;

public interface URegisterDsGateway {
    boolean checkIfUserExistsByEmail(String email);
    String saveUser(URegisterDsRequestModel requestModel);

}
