package uc.doc.submittest;

public interface SubTDocOutputBoundary {

        SubmitTDocResponseModel prepareSuccessView(SubmitTDocResponseModel message);

        SubmitTDocResponseModel prepareFailureView(String error);
}
