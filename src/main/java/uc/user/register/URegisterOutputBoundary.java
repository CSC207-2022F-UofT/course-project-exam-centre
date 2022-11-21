package uc.user.register;

public interface URegisterOutputBoundary {
    URegisterResponseModel prepareSuccessView(URegisterResponseModel user);

    URegisterResponseModel prepareFailView(String error);
}
