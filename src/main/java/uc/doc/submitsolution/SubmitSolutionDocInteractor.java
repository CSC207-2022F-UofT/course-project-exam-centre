package uc.doc.submitsolution;

import entities.*;

import java.time.LocalDateTime;

public class SubmitSolutionDocInteractor implements SubmitSDocInputBoundary{

    /**
     * The solution doc submission output boundary
     */
    private final SubSDocOutputBoundary sDocOutputBoundary;

    /**
     * An instance of the solution doc submission gateway
     */
    private final SubSDocDsGateway sDocDsGateway;

    /**
     * The program's state tracker for getting entities from their IDs
     */
    private final StateTracker stateTracker;

    /**
     * Creates an interactor for the solution doc submission use case
     * @param sDocDsGateway The solution document submission data storage gateway
     * @param sDocOutputBoundary The solution document submission use case output boundary
     * @param stateTracker The app's state tracker
     */
    public SubmitSolutionDocInteractor(SubSDocDsGateway sDocDsGateway,
                                       SubSDocOutputBoundary sDocOutputBoundary,
                                       StateTracker stateTracker) {
        this.sDocDsGateway = sDocDsGateway;
        this.sDocOutputBoundary = sDocOutputBoundary;
        this.stateTracker = stateTracker;
    }

    /**
     * Takes in the needed information for and creates a new SolutionDoc entity
     * @param model The solution document submission model containing all the relevant information for creating a new
     *              solution doc entity
     * @return If completed, the sucess view response model
     */
    @Override
    public SubSDocResponseModel submitSolutionDoc(SubSDocRequestModel model) {
        Course course  = stateTracker.getCourseIfTracked(model.getCourseID());
        User user = stateTracker.getCurrentUser();
        TestDocument parentTest = course.getTest(model.getParentTestID());

        SubSDocDsRequestModel dsRequestModel = new SubSDocDsRequestModel(
                model.getName(),
                user.getId(),
                model.getRecordedScore(),
                model.getCourseID(),
                model.getParentTestID(),
                model.getFilePath(),
                "",
                0,
                model.getRecordedTime()
        );

        String solutionId = sDocDsGateway.saveSolutionDocument(dsRequestModel);
        String rootMessageId = sDocDsGateway.addRootMessage(
                solutionId,
                user.getId()
        );

        sDocDsGateway.updateRootMessageIdOfSolution(
                solutionId,
                rootMessageId);

        SolutionDocument document = SolutionDocFactory.create(
                model.getName(),
                solutionId,
                course,
                user,
                model.getRecordedScore(),
                parentTest,
                model.getRecordedTime(),
                rootMessageId);

        parentTest.addUpdateSolution(document);

        SubSDocResponseModel responseModel = new SubSDocResponseModel(document.getId(), parentTest.getId(), LocalDateTime.now());

        return sDocOutputBoundary.prepareSucessView(responseModel);
    }
}
