package uc.doc.submitsolution;

import entities.*;
import entities.factories.SolutionDocFactory;

import java.time.LocalDateTime;

public class SubmitSolutionDocInteractor implements SubmitSDocInputBoundary{

    private final SubSDocOutputBoundary sDocOutputBoundary;
    private final SubSDocDsGateway sDocDsGateway;
    private final SubSDocFileAccessGateway sDocFileAccessGateway;
    private final StateTracker stateTracker;
    private final SolutionDocFactory solutionDocFactory;

    public SubmitSolutionDocInteractor(SubSDocDsGateway sDocDsGateway,
                                       SubSDocFileAccessGateway sDocFileAccessGateway,
                                       SubSDocOutputBoundary sDocOutputBoundary,
                                       StateTracker stateTracker,
                                       SolutionDocFactory solutionDocFactory) {
        this.sDocDsGateway = sDocDsGateway;
        this.sDocFileAccessGateway = sDocFileAccessGateway;
        this.sDocOutputBoundary = sDocOutputBoundary;
        this.stateTracker = stateTracker;
        this.solutionDocFactory = solutionDocFactory;
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

        sDocFileAccessGateway.uploadSolutionDocument(dsRequestModel, solutionId);

        SolutionDocument document = solutionDocFactory.create(
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
