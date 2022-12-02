package uc.doc.submitsolution;

import entities.*;

import java.time.LocalDateTime;

public class SubmitSolutionDocInteractor implements SubmitSDocInputBoundary{

    private final SubSDocOutputBoundary sDocOutputBoundary;

    private final SubSDocDsGateway sDocDsGateway;

    private final StateTracker stateTracker;

    public SubmitSolutionDocInteractor(SubSDocDsGateway sDocDsGateway,
                                       SubSDocOutputBoundary sDocOutputBoundary,
                                       StateTracker stateTracker) {
        this.sDocDsGateway = sDocDsGateway;
        this.sDocOutputBoundary = sDocOutputBoundary;
        this.stateTracker = stateTracker;
    }

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
