package uc.doc.voteonsolution;

import entities.*;
import entities.factories.CourseFactory;
import entities.factories.SolutionDocFactory;
import entities.factories.TestDocFactory;
import entities.factories.UserFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VoteOnSolutionDocInteractorTest {
    private User user;
    private Course course;
    private TestDocument testDoc;
    private SolutionDocument solutionDoc;
    private StateTracker stateTracker;
    private int storedVoteTotal;
    @Before
    public void setup(){
        SolutionDocFactory solutionDocFactory = new SolutionDocFactory();
        CourseFactory courseFactory = new CourseFactory();
        UserFactory userFactory = new UserFactory();
        TestDocFactory testDocFactory = new TestDocFactory();

        user = userFactory.create("First", "Last", "firstlast@mail.uoftears.ca", "userId");
        course = courseFactory.create("Software Design","CSC207", "courseId");
        testDoc = testDocFactory.create("Final Exam #1", "testId", course, user, 0.0F, 1, "Final");
        solutionDoc = solutionDocFactory.create("Solution", "solutionId", course, user, 0.0F, testDoc, 0.0F, "rootMsgId");

        course.addTest(testDoc);
        testDoc.addUpdateSolution(solutionDoc);

        stateTracker = new StateTracker();
        stateTracker.setCurrentUser(user);
        stateTracker.addUpdateTrackedCourse(course);

        storedVoteTotal = 0;
    }

    /**
     * Test that voteSolutionDoc increments solution document vote total when vote is true.
     */
    @Test
    public void voteSolutionDocIncrementsVoteTotal() {

        VoteSDocDsGateway dsGateway = new VoteSDocDsGateway() {
            @Override
            public int getVoteTotalBySolutionIdQuery(String solutionId) {
                return storedVoteTotal;
            }

            @Override
            public boolean updateSolutionDocVote(VoteSDocDsRequestModel model) {
                // check if persistence is correctly updated
                assertEquals(storedVoteTotal + 1, model.getVote());
                assertEquals(solutionDoc.getId(), model.getSolutionId());
                assertEquals(user.getId(), model.getUserId());
                return true;
            }

            @Override
            public String getTestIdBySolutionId(String solutionId) {
                return testDoc.getId();
            }

            @Override
            public String getCourseIdByTestId(String testId) {
                return course.getId();
            }
        };

        VoteSDocOutputBoundary presenter = new VoteSDocOutputBoundary() {
            @Override
            public VoteSDocResponseModel prepareSuccessView(VoteSDocResponseModel model) {
                // check if output data is correct
                assertEquals(course.getId(), model.getCourseId());
                assertEquals(testDoc.getId(), model.getTestId());
                assertEquals(solutionDoc.getId(), model.getSolutionId());
                assertEquals(storedVoteTotal + 1, model.getVoteTotal());
                return null;
            }

            @Override
            public VoteSDocResponseModel prepareFailureView(String errorMessage) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        VoteSDocRequestModel requestModel = new VoteSDocRequestModel(solutionDoc.getId(), true);
        VoteOnSolutionDocInteractor interactor = new VoteOnSolutionDocInteractor(dsGateway, presenter, stateTracker);
        interactor.voteSolutionDoc(requestModel);
    }


    /**
     * Test that voteSolutionDoc increments solution document vote total when vote is false.
     */
    @Test
    public void voteSolutionDocDecrementsVoteTotal() {
        VoteSDocDsGateway dsGateway = new VoteSDocDsGateway() {
            @Override
            public int getVoteTotalBySolutionIdQuery(String solutionId) {
                return storedVoteTotal;
            }

            @Override
            public boolean updateSolutionDocVote(VoteSDocDsRequestModel model) {
                // check if persistence is correctly updated
                assertEquals(storedVoteTotal - 1, model.getVote());
                assertEquals(solutionDoc.getId(), model.getSolutionId());
                assertEquals(user.getId(), model.getUserId());
                return true;
            }

            @Override
            public String getTestIdBySolutionId(String solutionId) {
                return testDoc.getId();
            }

            @Override
            public String getCourseIdByTestId(String testId) {
                return course.getId();
            }
        };

        VoteSDocOutputBoundary presenter = new VoteSDocOutputBoundary() {
            @Override
            public VoteSDocResponseModel prepareSuccessView(VoteSDocResponseModel model) {
                // check if output data is correct
                assertEquals(course.getId(), model.getCourseId());
                assertEquals(testDoc.getId(), model.getTestId());
                assertEquals(solutionDoc.getId(), model.getSolutionId());
                assertEquals(storedVoteTotal - 1, model.getVoteTotal());
                return null;
            }

            @Override
            public VoteSDocResponseModel prepareFailureView(String errorMessage) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        VoteSDocRequestModel requestModel = new VoteSDocRequestModel(solutionDoc.getId(), false);
        VoteOnSolutionDocInteractor interactor = new VoteOnSolutionDocInteractor(dsGateway, presenter, stateTracker);
        interactor.voteSolutionDoc(requestModel);
    }
}