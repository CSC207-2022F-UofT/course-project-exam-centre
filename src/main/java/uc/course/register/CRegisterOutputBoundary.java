package uc.course.register;

public interface CRegisterOutputBoundary {
    CRegisterResponseModel prepareSuccessView (CRegisterResponseModel responseModel);
    CRegisterResponseModel prepareFailView (String errorMessage);

}
