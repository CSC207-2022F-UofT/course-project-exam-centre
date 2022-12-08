package uc.doc.voteonsolution;

import entities.*;
import entities.factories.CourseFactory;
import entities.factories.SolutionDocFactory;
import entities.factories.TestDocFactory;
import entities.factories.UserFactory;
import org.junit.Before;

import static org.junit.Assert.*;

public class VoteOnSolutionDocInteractorTest {

    private SolutionDocument solution1;
    private SolutionDocument solution2;
    private StateTracker stateTracker;

    @Before
    public void setup(){
        SolutionDocFactory solutionDocFactory = new SolutionDocFactory();
        CourseFactory courseFactory = new CourseFactory();
        UserFactory userFactory = new UserFactory();
        TestDocFactory testDocFactory = new TestDocFactory();

        User user = userFactory.create("First",
                                        "Last",
                                        "firstlast@mail.uoftears.ca",
                                        "userId");

        Course course = courseFactory.create("Software Design",
                                            "CSC207",
                                            "courseId");

        TestDocument testDoc = testDocFactory.create("Final Exam #1", "testid", course, user, 0.0F, 1, "Final");

        solution1 = solutionDocFactory.create("Solution #1", "solution1Id", course, user, 0.0F, testDoc, 0.0F, "rootMsgId");
        solution2 = solutionDocFactory.create("Solution #2", "solution2Id", course, user, 0.0F, testDoc, 0.0F, "rootMsgId");
        stateTracker = new StateTracker();
        stateTracker.setCurrentUser(user);
    }

    public void upvoteIncrementsVoteTotal() {

        VoteSDocDsGateway dsGateway = new VoteSDocDsGateway() {
            @Override
            public int getVoteTotalBySolutionIdQuery(String solutionId) {
                return 0;
            }

            @Override
            public boolean updateSolutionDocVote(VoteSDocDsRequestModel model) {
                assertEquals(1, model.getVote());
                assertEquals("solution1Id", model.getSolutionId());
                assertEquals("userId", model.getUserId());
                return true;
            }
        };

        VoteSDocOutputBoundary presenter = new VoteSDocOutputBoundary() {
            @Override
            public VoteSDocResponseModel prepareSuccessView(VoteSDocResponseModel model) {
                assertEquals(1, model.getVoteTotal());
                return null;
            }

            @Override
            public VoteSDocDsRequestModel prepareFailureView(String errorMessage) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        VoteOnSolutionDocInteractor interactor = new VoteOnSolutionDocInteractor(dsGateway, presenter, stateTracker);

        // TODO: Create response models
    }

    public void downvoteDecrementsVoteTotal() {
        // TODO
    }
}