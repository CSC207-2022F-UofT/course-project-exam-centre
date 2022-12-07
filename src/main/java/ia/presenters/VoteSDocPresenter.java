package ia.presenters;

import fworks.views.ViewManagerGateway;
import uc.doc.voteonsolution.VoteSDocDsRequestModel;
import uc.doc.voteonsolution.VoteSDocOutputBoundary;
import uc.doc.voteonsolution.VoteSDocResponseModel;

public class VoteSDocPresenter implements VoteSDocOutputBoundary{

    private final ViewManagerGateway viewManagerGateway;

    public VoteSDocPresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }

    @Override
    public VoteSDocResponseModel prepareSuccessView(VoteSDocResponseModel model) {
        return model;
    }

    @Override
    public VoteSDocDsRequestModel prepareFailureView(String errorMessage) {
        // TODO prepare failure view
        viewManagerGateway.showError(errorMessage, "Document Vote Failed");
        return null;
    }
    
}
