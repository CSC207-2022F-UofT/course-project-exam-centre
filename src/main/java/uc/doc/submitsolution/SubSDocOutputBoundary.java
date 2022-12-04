package uc.doc.submitsolution;

public interface SubSDocOutputBoundary {

    SubSDocResponseModel prepareSuccessView(SubSDocResponseModel model);
    SubSDocResponseModel prepareFailView(String errorMessage);

}
