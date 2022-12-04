package uc.doc.submittest;

public interface SubTDocOutputBoundary {

        SubTDocResponseModel prepareSuccessView(SubTDocResponseModel message);
        SubTDocResponseModel prepareFailView(String error);
}
