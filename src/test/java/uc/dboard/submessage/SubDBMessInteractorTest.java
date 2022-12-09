package uc.dboard.submessage;

import entities.*;
import entities.factories.*;
import org.junit.Test;
import uc.dboard.submessage.dbmodels.SubDBMessUserDbModel;

import static org.junit.Assert.*;

public class SubDBMessInteractorTest {
    @Test
    public void SubDBMessSuccess(){
        // Assert if submitting a message was successful and the related Solution document and message tree is updated accordingly.
        SubDBMessRequestModel requestModel = new SubDBMessRequestModel("0000", "1111", "2222","PLEASE SUBMIT THIS MESSAGE");
        SubDBMessUserDbModel subDBMessUserDbModel = new SubDBMessUserDbModel() {
            /* Initializing a SubDBMessUserDbModel that returns some dummy data to test.
             */
            @Override
            public String getUserId() {
                return "1111";
            }

            @Override
            public String getFirstName() {
                return "BOB";
            }

            @Override
            public String getLastName() {
                return "MAN";
            }

            @Override
            public String getEmail() {
                return "BOBMAN@GMAIL.COM";
            }
        };
        SubDBMessDsGateway dsGateway = new SubDBMessDsGateway() {
            /* Initializing a SubDBMessDSGateway that returns some dummy data to test.
            */
            @Override
            public String getTestIdBySolutionId(String solutionId) {
                return "3333";
            }

            @Override
            public String getCourseIdByTestId(String testId) {
                return "4444";
            }

            @Override
            public String addMessage(SubDBMessDsRequestModel requestModel) {
                return "5555";
            }

            @Override
            public SubDBMessUserDbModel getUserById(String userId) {
                return subDBMessUserDbModel;
            }
        };
        SubDBMessOutputBoundary presenter = new SubDBMessOutputBoundary() {
            /* Initializing a SubDBMessOutputBoundary that asserts all the appropriate checks once the prepareSuccessView is called.
             */
            @Override
            public SubDBMessResponseModel prepareSuccessView(SubDBMessResponseModel message) {
                // Check if the ParentTestId Matches
                assertEquals(message.getParentTestId(), "3333");
                // Check if the SolutionId Matches
                assertEquals(message.getSolutionId(), "0000");
                // Check if the ParentCourseId Matches
                assertEquals(message.getParentCourseId(), "4444");
                // Check if the MessageTree Matches
                assertEquals(message.getMessageTree().getReplies().get(0).getMessageBody(), "PLEASE SUBMIT THIS MESSAGE");

                return null;
            }

            @Override
            public SubDBMessResponseModel prepareFailView(String error) {
                fail("Interactor should not fail");
                return null;
            }
        };
        StateTracker stateTracker = new StateTracker();

        //Initializing a mock Course
        CourseFactory courseFactory = new CourseFactory();
        Course course = courseFactory.create("MATH", "MAT137", "4444");

        //Initializing a mock User
        UserFactory userFactory = new UserFactory();
        User user = userFactory.create("BOB", "MAN", "BOBMAN@GMAIL.COM", "1111");

        //Initializing a mock Test
        TestDocFactory testDocFactory = new TestDocFactory();
        TestDocument test = testDocFactory.create("MATH 1", "3333", course, user, 10f, 4, "TERM TEST");

        //Initializing a mock Solution Document
        SolutionDocFactory solutionDocFactory = new SolutionDocFactory();
        SolutionDocument solution = solutionDocFactory.create("MATH 1 SOLUTION", "0000", course, user, 40f, test, 45f, "2222");

        //Connecting all the mock data together and adding the course to the statetracker.
        course.addTest(test);
        test.addUpdateSolution(solution);
        stateTracker.addUpdateTrackedCourse(course);

        MessageFactory messageFactory = new MessageFactory();
        SubmitDBMessageInteractor interactor = new SubmitDBMessageInteractor(presenter, messageFactory, dsGateway, stateTracker);
        interactor.submitMessage(requestModel);
    }
}
