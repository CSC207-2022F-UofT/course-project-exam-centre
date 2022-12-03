package uc.dboard.submessage;

// Use case layer

public interface SubDBMessOutputBoundary {
    SubDBMessResponseModel prepareSuccessView(SubDBMessResponseModel message);

    SubDBMessResponseModel prepareFailView(String error);
}