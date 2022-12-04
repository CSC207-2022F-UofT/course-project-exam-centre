package ia.presenters;

import uc.doc.voteonsolution.VoteSDocDsRequestModel;
import uc.doc.voteonsolution.VoteSDocOutputBoundary;
import uc.doc.voteonsolution.VoteSDocResponseModel;

public class VoteSDocPresenter implements VoteSDocOutputBoundary{

    @Override
    public VoteSDocResponseModel prepareSuccessView(VoteSDocResponseModel model) {
        return model;
    }

    @Override
    public VoteSDocDsRequestModel prepareFailureView(VoteSDocResponseModel model) {
        // TODO prepare failure view
        return null;
    }
    
}
