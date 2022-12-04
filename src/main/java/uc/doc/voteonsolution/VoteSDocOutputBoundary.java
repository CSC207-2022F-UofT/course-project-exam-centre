package uc.doc.voteonsolution;

public interface VoteSDocOutputBoundary {

    VoteSDocResponseModel prepareSuccessView(VoteSDocResponseModel model);

    VoteSDocDsRequestModel prepareFailureView(VoteSDocResponseModel model);
    
}
