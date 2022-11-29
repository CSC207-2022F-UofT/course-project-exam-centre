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
        User user = stateTracker.getUserIfTracked(model.getUserID());
        TestDocument parentTest = course.getTest(model.getParentTestID());

        SubSDocDsRequestModel dsRequestModel = new SubSDocDsRequestModel(
                model.getName(),
                model.getUserID(),
                model.getRecordedScore(),
                model.getCourseID(),
                model.getParentTestID(),
                model.getFilePath()
        );

        String docID = sDocDsGateway.saveSolutionDocument(dsRequestModel);

        SolutionDocument document = SolutionDocFactory.create(
                model.getName(),
                docID,
                course,
                user,
                model.getRecordedScore(),
                parentTest,
                model.getRecordedTime(),
                model.getRootID());

        parentTest.addUpdateSolution(document);

        SubSDocResponseModel responseModel = new SubSDocResponseModel(document.getId(), parentTest.getId(), LocalDateTime.now());

        return sDocOutputBoundary.prepareSucessView(responseModel);
    }
}
