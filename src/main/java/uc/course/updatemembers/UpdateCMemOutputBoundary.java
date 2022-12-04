package uc.course.updatemembers;

public interface UpdateCMemOutputBoundary {
    UpdateCMemResponseModel prepareSuccessView(UpdateCMemResponseModel responseModel);
    UpdateCMemResponseModel prepareFailView(String errorMessage);
}
