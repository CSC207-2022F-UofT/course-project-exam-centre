package uc.doc.voteonsolution;

import entities.SolutionDocument;

public interface VoteSDocInputBoundary {
    
    VoteSDocResponseModel voteSolutionDoc(VoteSDocRequestModel model, SolutionDocument sDocument);

}
