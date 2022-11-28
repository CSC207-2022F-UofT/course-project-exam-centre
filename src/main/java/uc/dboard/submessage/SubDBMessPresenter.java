package uc.dboard.submessage;

// Use case layer

public interface SubDBMessPresenter {
    SubDBMessResponseModel prepareSuccessView(SubDBMessResponseModel message);

    SubDBMessResponseModel prepareFailView(String error);
}