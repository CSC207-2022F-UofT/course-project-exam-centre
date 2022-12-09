package usecases.course.updatemembers;

import entities.*;
import entities.factories.*;
import usecases.course.updatemembers.dbmodels.*;
import usecases.course.updatemembers.responsemodels.*;

import java.util.*;

/** UpdateCourseMembershipInteractor implements the ability to update a user's course membership.
 * @layer use cases
 */
public class UpdateCourseMembershipInteractor implements UpdateCMemInputBoundary {

    private final UpdateCMemDsGateway dsGateway;
    private final UpdateCMemOutputBoundary presenter;
    private final StateTracker currentState;

    private final UserFactory userFactory;
    private final TestDocFactory testDocFactory;
    private final CourseFactory courseFactory;
    private final SolutionDocFactory solutionDocFactory;
    private final MessageFactory messageFactory;

    /** Constructs an UpdateCourseMembershipInteractor with a gateway and an OutputBoundary
     *
     * @param gateway provides methods to access persistent memory
     * @param presenter provides methods to update the view
     * @param currentState the state tracker representing current software state
     * @param userFactory the user factory to create users
     * @param testDocFactory the test document factory to create tests
     * @param solutionDocFactory the solution document factory to create solutions
     * @param courseFactory the course factory to create courses
     * @param messageFactory the message factory to create messages
     */
    public UpdateCourseMembershipInteractor(
            UpdateCMemDsGateway gateway,
            UpdateCMemOutputBoundary presenter,
            StateTracker currentState,
            UserFactory userFactory,
            CourseFactory courseFactory,
            TestDocFactory testDocFactory,
            SolutionDocFactory solutionDocFactory,
            MessageFactory messageFactory) {
        this.dsGateway = gateway;
        this.presenter = presenter;
        this.currentState = currentState;
        this.userFactory = userFactory;
        this.testDocFactory = testDocFactory;
        this.solutionDocFactory = solutionDocFactory;
        this.courseFactory = courseFactory;
        this.messageFactory = messageFactory;
    }

    /**
     * @param messageTree a tree representing messages and its replies
     * @return a response model of the tree
     */
    private UpdateCMemMessageTreeResponseModel prepareMessageTreeResponseModel(
            MessageTree messageTree) {

        Message currentMessage = messageTree.getRootMessage();

        UpdateCMemUserDbModel senderDbModel
                = dsGateway.getUserById(currentMessage.getUserId());

        List<UpdateCMemMessageTreeResponseModel> replies = new ArrayList<>();

        UpdateCMemUserResponseModel senderUserModel
                = new UpdateCMemUserResponseModel(
                senderDbModel.getUserId(),
                senderDbModel.getEmail(),
                senderDbModel.getFirstName(),
                senderDbModel.getLastName()
        );

        if (messageTree.getSubtrees().size() > 0) {
            for(MessageTree replyMessageTree : messageTree.getSubtrees()) {
                replies.add(
                        prepareMessageTreeResponseModel(replyMessageTree)
                );
            }
        }

        return new UpdateCMemMessageTreeResponseModel(
                currentMessage.getMessageId(),
                senderUserModel,
                currentMessage.getBody(),
                currentMessage.getDate(),
                replies
        );

    }

    /**
     * @param solutionDocEntity a solutions document
     * @return a response model of the solutions document
     */
    private UpdateCMemSolutionDocResponseModel prepareSolutionDocResponseModel(
            SolutionDocument solutionDocEntity) {
        UpdateCMemMessageTreeResponseModel messageTree
                = prepareMessageTreeResponseModel(
                solutionDocEntity.getMessageTree()
        );
        return new UpdateCMemSolutionDocResponseModel(
                solutionDocEntity.getId(),
                solutionDocEntity.getVotes(),
                solutionDocEntity.getScore(),
                solutionDocEntity.getRecordedTime(),
                solutionDocEntity.getName(),
                messageTree
        );
    }

    /**
     * @param testDocEntity a test document
     * @return a response model of the test document
     */
    private UpdateCMemTestDocResponseModel prepareTestDocResponseModel(
            TestDocument testDocEntity) {
        Map<String, UpdateCMemSolutionDocResponseModel> solutionModels = new HashMap<>();
        for(SolutionDocument currentSolution : testDocEntity.getSolutions().values()) {
            solutionModels.put(
                    currentSolution.getId(),
                    prepareSolutionDocResponseModel(currentSolution)
            );
        }
        return new UpdateCMemTestDocResponseModel(
                testDocEntity.getId(),
                testDocEntity.getUser().getId(),
                testDocEntity.getTestType(),
                testDocEntity.getNumberOfQuestions(),
                testDocEntity.getEstimatedTime(),
                testDocEntity.getName(),
                solutionModels
        );
    }

    /**
     * @param courseEntity a course
     * @return a response model of the course
     */
    private UpdateCMemCourseResponseModel prepareCourseResponseModel(
            Course courseEntity) {
        Map<String, UpdateCMemTestDocResponseModel> testModels = new HashMap<>();
        for(TestDocument currentTest : courseEntity.getTests().values()) {
            testModels.put(
                    currentTest.getId(),
                    prepareTestDocResponseModel(currentTest)
            );
        }
        return new UpdateCMemCourseResponseModel(
                courseEntity.getId(),
                courseEntity.getCourseCode(),
                courseEntity.getCourseName(),
                testModels
        );
    }

    /**
     * @return a update course response model
     */
    private UpdateCMemResponseModel prepareResponseModel() {
        User currentUser = currentState.getCurrentUser();
        Map<String, UpdateCMemCourseResponseModel> usersCourseModels = new HashMap<>();

        if (currentUser != null) {

            // Construct current user response model
            UpdateCMemUserResponseModel currentUserModel =
                    new UpdateCMemUserResponseModel(
                            currentUser.getId(),
                            currentUser.getEmail(),
                            currentUser.getFirstName(),
                            currentUser.getLastName()
                    );

            Collection<Course> currentUserCourses =
                    currentState.getAllTrackedCourses().values();

            // Construct course response models for current user
            for (Course currentUserCourse : currentUserCourses) {
                usersCourseModels.put(
                        currentUserCourse.getId(),
                        prepareCourseResponseModel(currentUserCourse)
                );
            }

            // Construct response model
            return new UpdateCMemResponseModel(
                    currentUserModel,
                    usersCourseModels
            );

        } else {
            // Prepare fail view
            return presenter.prepareFailView("No current user session");
        }

    }

    /**
     * @param userId a unique identifier of a user
     * @return the user
     */
    private User constructUserById(String userId) {
        UpdateCMemUserDbModel userData = dsGateway.getUserById(userId);
        List<String> rawUserEnrolmentsData = dsGateway.getCourseIdsByUserId(userId);

        User newUser = userFactory.create(
                userData.getFirstName(),
                userData.getLastName(),
                userData.getEmail(),
                userData.getUserId());

        for (String rawUserEnrolmentsDatum : rawUserEnrolmentsData) {
            newUser.addCourse(rawUserEnrolmentsDatum);
        }

        return newUser;
    }

    /**
     * @param parentTestDoc a test document that is a parent of solution documents
     */
    private void constructSolutionsByTest(TestDocument parentTestDoc) {
        String parentTestId = parentTestDoc.getId();
        List<? extends UpdateCMemSolutionDocDbModel> solutionsData
                = dsGateway.getSolutionDocsByTestId(parentTestId);

        SolutionDocument currentSolutionDoc;

        for (UpdateCMemSolutionDocDbModel currentSolutionData: solutionsData) {

            currentSolutionDoc = solutionDocFactory.create(
                    currentSolutionData.getSolutionName(),
                    currentSolutionData.getSolutionId(),
                    parentTestDoc.getCourse(),
                    constructUserById(currentSolutionData.getUserId()),
                    currentSolutionData.getRecordedScore(),
                    parentTestDoc,
                    currentSolutionData.getEstimatedTime(),
                    currentSolutionData.getRootMessageId()
            );

            constructMessageTreeByParentId(
                    currentSolutionData.getRootMessageId(),
                    currentSolutionDoc.getMessageTree()
            );

            parentTestDoc.addUpdateSolution(currentSolutionDoc);

        }
    }

    /**
     * @param parentId a unique identifier of a parent of a message
     * @param newMessageTree the message tree
     */
    private void constructMessageTreeByParentId(
            String parentId, MessageTree newMessageTree) {
        List<? extends UpdateCMemMessageDbModel> rawChildMessageData
                = dsGateway.getMessagesByParentId(parentId);

        for (UpdateCMemMessageDbModel rawChildMessageDatum : rawChildMessageData) {

            newMessageTree.addMessage(
                    messageFactory.create(
                            rawChildMessageDatum.getMessageId(),
                            rawChildMessageDatum.getSolutionId(),
                            rawChildMessageDatum.getUserId(),
                            rawChildMessageDatum.getParentId(),
                            rawChildMessageDatum.getMessageBody(),
                            rawChildMessageDatum.getMessageSentTimestamp()
                    )
            );
            constructMessageTreeByParentId(
                    rawChildMessageDatum.getMessageId(),
                    newMessageTree);
        }
    }

    /**
     * @param parentCourse a course
     */
    private void constructAllTestsByCourse(Course parentCourse) {
        String courseId = parentCourse.getId();
        List<? extends UpdateCMemTestDocDbModel> testsData
                = dsGateway.getTestDocsByCourseId(courseId);
        TestDocument currentTestDoc;

        for (UpdateCMemTestDocDbModel currentTestData : testsData) {

            currentTestDoc = testDocFactory.create(
                    currentTestData.getTestName(),
                    currentTestData.getTestId(),
                    parentCourse,
                    constructUserById(
                            currentTestData.getUserId()
                    ),
                    currentTestData.getEstimatedTime(),
                    currentTestData.getNumOfQuestions(),
                    currentTestData.getTestType()
            );

            constructSolutionsByTest(currentTestDoc);
            parentCourse.addTest(currentTestDoc);

        }
    }

    /**
     * @param courseId a unique identifier of a course
     * @return the course
     */
    private Course constructCourseById(String courseId) {
        UpdateCMemCourseDbModel courseData = dsGateway.getCourseById(courseId);

        Course newCourse = courseFactory.create(
                courseData.getCourseName(),
                courseData.getCourseCode(),
                courseData.getCourseId()
        );

        constructAllTestsByCourse(newCourse);

        return newCourse;

    }

    /**
     * @param requestModel UpdateCMemRequestModel containing the information of the user
     *                     and courses they want to be enrolled in
     * @return a response model for updating course membership
     */
    @Override
    public UpdateCMemResponseModel updateCourseMembership(UpdateCMemRequestModel requestModel) {
        User currentUser = currentState.getCurrentUser();
        String userId = currentUser.getId();
        List<String> enrollments = dsGateway.getCourseIdsByUserId(userId);
        List<String> newCourseList = requestModel.getNewCoursesList();
        Course newCourse;

        if (!dsGateway.getConnectionStatus()) {
            return presenter.prepareFailView("Database Connection Failed");
        }

        // Add new course enrolments
        for (String courseId: newCourseList) { //Checks for new courses user not currently enrolled in.
            if (!enrollments.contains(courseId)) {
                if (dsGateway.checkIfCourseExists(courseId)) { //checks if a course has been registered
                    dsGateway.addCourseEnrolment(courseId, userId);
                    currentUser.addCourse(courseId);
                    newCourse = constructCourseById(courseId);
                    currentState.addUpdateTrackedCourse(newCourse);
                }
                else {
                    return presenter.prepareFailView(courseId + " has not been registered");
                }
            }
        }

        // Remove course enrolments
        for (String courseId: enrollments) { //Checks for removed courses
            if (!newCourseList.contains(courseId)) {
                dsGateway.removeCourseEnrolment(courseId, userId);
                currentUser.removeCourse(courseId);
                currentState.removeTrackedCourse(courseId);
            }
        }

        UpdateCMemResponseModel responseModel = prepareResponseModel();
        return presenter.prepareSuccessView(responseModel);

    }
}