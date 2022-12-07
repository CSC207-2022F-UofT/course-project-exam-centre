package ia.presenters;

import fworks.views.ViewManagerGateway;
import uc.doc.voteonsolution.VoteSDocDsRequestModel;
import uc.doc.voteonsolution.VoteSDocOutputBoundary;
import uc.doc.voteonsolution.VoteSDocResponseModel;

public class VoteSDocPresenter implements VoteSDocOutputBoundary{

    @Override
    public VoteSDocResponseModel prepareSuccessView(VoteSDocResponseModel model) {
        return model;
    }

    @Override
    public VoteSDocDsRequestModel prepareFailureView(String errorMessage) {
        // TODO prepare failure view
        ViewManagerGateway.showError(errorMessage, "Document Vote Failed");
        return null;
    }
    
}
