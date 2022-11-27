package uc.state.update;

public interface UpdateStateOutputBoundary {
    UpdateStateResponseModel prepareSuccessView (UpdateStateResponseModel responseModel);
    UpdateStateResponseModel prepareFailView (String errorMessage);

}
