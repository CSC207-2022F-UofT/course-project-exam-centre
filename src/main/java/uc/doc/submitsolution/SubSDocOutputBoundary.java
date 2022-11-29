package uc.doc.submitsolution;

public interface SubSDocOutputBoundary {

    SubSDocResponseModel prepareSucessView(SubSDocResponseModel model);

    SubSDocRequestModel prepareFailureView(SubSDocResponseModel model);

}
