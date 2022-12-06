package uc.doc.voteonsolution;

public interface VoteSDocDsGateway {

    int getVoteTotalBySolutionIdQuery(String solutionId);

    boolean updateSolutionDocVote(VoteSDocDsRequestModel model);

}
