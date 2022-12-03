package uc.doc.submitsolution;

public interface SubSDocDsGateway {

    String saveSolutionDocument(SubSDocDsRequestModel model);

    String addRootMessage(String solutionId,
                          String userId);

    void updateRootMessageIdOfSolution(
            String solutionId,
            String rootMessageId);

}
