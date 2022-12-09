package usecases.state.update;

import entities.*;
import entities.factories.*;
import usecases.state.update.dbmodels.*;
import usecases.state.update.responsemodels.*;

import java.util.*;

/**
 * The interactor for the update state use case. This will poll the persistent storage database and
 * reconstruct all entities relevant to the current user's state. This will keep the current state
 * in sync with changes made by other users, such as new test document submissions and new discussion
 * board messages.
 */
public class UpdateStateInteractor implements UpdateStateInputBoundary {
    private final UpdateStateOutputBoundary presenter;
    private final UpdateStateDsGateway dsGateway;
    private final StateTracker currentState;

    private final UserFactory userFactory;
    private final TestDocFactory testDocFactory;
    private final CourseFactory courseFactory;
    private final SolutionDocFactory solutionDocFactory;
    private final MessageFactory messageFactory;

    /**
     * Construct new UpdateStateInteractor
     *
     * @param presenter the output boundary for update state use case
     * @param dsGateway the DS gateway for update state use case
     * @param stateTracker the current state
     * @param userFactory a user entity factory
     * @param courseFactory a course entity factory
     * @param testDocFactory a test doc factory
     * @param solutionDocFactory a solution doc factory
     * @param messageFactory a message factory
     */
    public UpdateStateInteractor(
            UpdateStateOutputBoundary presenter,
            UpdateStateDsGateway dsGateway,
            StateTracker stateTracker,
            UserFactory userFactory,
            CourseFactory courseFactory,
            TestDocFactory testDocFactory,
            SolutionDocFactory solutionDocFactory,
            MessageFactory messageFactory) {

        this.presenter = presenter;
        this.dsGateway = dsGateway;
        this.currentState = stateTracker;

        this.userFactory = userFactory;
        this.testDocFactory = testDocFactory;
        this.solutionDocFactory = solutionDocFactory;
        this.courseFactory = courseFactory;
        this.messageFactory = messageFactory;
    }

    /**
     * Construct user entity by ID from persistent storage.
     * @param userId the user ID for the entity to be constructed
     * @return a newly-constructed User entity
     */
    private User constructUserById(String userId) {
        UpdateStateUserDbModel userData = dsGateway.getUserById(userId);
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
     * Construct CourseInfo item from persistent storage by ID.
     * @param courseId the ID of the course to be constructed
     * @return the newly-constructed CourseInfo object
     */
    private CourseInfo constructCourseInfoItemById(String courseId) {
        UpdateStateCourseDbModel courseData = dsGateway.getCourseById(courseId);

        return courseFactory.create(
                courseData.getCourseName(),
                courseData.getCourseCode(),
                courseData.getCourseId()
        );
    }

    /**
     * Construct course entity by ID from persistent storage.
     * @param courseId course ID of entity to be constructed
     * @return a newly constructed Course object
     */
    private Course constructCourseById(String courseId) {
        UpdateStateCourseDbModel courseData = dsGateway.getCourseById(courseId);

        Course newCourse = courseFactory.create(
                courseData.getCourseName(),
                courseData.getCourseCode(),
                courseData.getCourseId()
        );

        constructAllTestsByCourse(newCourse);

        return newCourse;

    }

    /**
     * Construct all test documents for a course entity from persistent storage.
     * @param parentCourse the course entity being updated
     */
    private void constructAllTestsByCourse(Course parentCourse) {
        String courseId = parentCourse.getId();
        List<? extends UpdateStateTestDocDbModel> testsData
                = dsGateway.getTestDocsByCourseId(courseId);
        TestDocument currentTestDoc;

        for (UpdateStateTestDocDbModel currentTestData : testsData) {

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
     * Construct solutions entities for a given test entity from persistent storage.
     * @param parentTestDoc the test document being updated
     */
    private void constructSolutionsByTest(TestDocument parentTestDoc) {
        String parentTestId = parentTestDoc.getId();
        List<? extends UpdateStateSolutionDocDbModel> solutionsData
                = dsGateway.getSolutionDocsByTestId(parentTestId);

        SolutionDocument currentSolutionDoc;

        for (UpdateStateSolutionDocDbModel currentSolutionData: solutionsData) {

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
     * Recursively construct message tree entity from persistent storage using parent ID.
     * @param parentId represents the parent ID being checked
     * @param newMessageTree the recursively constructed message tree entity
     */
    private void constructMessageTreeByParentId(
            String parentId, MessageTree newMessageTree) {
            List<? extends UpdateStateMessageDbModel> rawChildMessageData
                    = dsGateway.getMessagesByParentId(parentId);

        for (UpdateStateMessageDbModel rawChildMessageDatum : rawChildMessageData) {

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
     * Construct all course info items from persistent storage.
     * @return map of course IDs to CourseInfo entities
     */
    private Map<String, CourseInfo> constructAllCourseInfoItems() {
        List<String> rawCourseIdsData = dsGateway.getAllCourseIds();
        Map<String, CourseInfo> allCourseItems = new HashMap<>();
        CourseInfo currentCourseInfoItem;

        for (String rawCourseIdsDatum : rawCourseIdsData) {
            currentCourseInfoItem = constructCourseInfoItemById(rawCourseIdsDatum);
            allCourseItems.put(
                    currentCourseInfoItem.getId(),
                    currentCourseInfoItem
            );
        }
        return allCourseItems;
    }

    /**
     * Prepares a message tree response model
     * @param messageTree the entity to be converted to a response model
     * @return a response model representing a message tree entity
     */
    private UpdateStateMessageTreeResponseModel prepareMessageTreeResponseModel(
            MessageTree messageTree) {

        Message currentMessage = messageTree.getRootMessage();

        UpdateStateUserDbModel senderDbModel
                = dsGateway.getUserById(currentMessage.getUserId());

        List<UpdateStateMessageTreeResponseModel> replies = new ArrayList<>();

        UpdateStateUserResponseModel senderUserModel
                = new UpdateStateUserResponseModel(
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

        return new UpdateStateMessageTreeResponseModel(
                currentMessage.getMessageId(),
                senderUserModel,
                currentMessage.getBody(),
                currentMessage.getDate(),
                replies
        );

    }

    /**
     * Prepare a solution document response model.
     * @param solutionDocEntity the entity to be converted to a response model
     * @return a response model representing a solution document entity
     */
    private UpdateStateSolutionDocResponseModel prepareSolutionDocResponseModel(
            SolutionDocument solutionDocEntity) {
        UpdateStateMessageTreeResponseModel messageTree
                = prepareMessageTreeResponseModel(
                        solutionDocEntity.getMessageTree()
        );
        return new UpdateStateSolutionDocResponseModel(
                solutionDocEntity.getId(),
                solutionDocEntity.getVotes(),
                solutionDocEntity.getScore(),
                solutionDocEntity.getRecordedTime(),
                solutionDocEntity.getName(),
                messageTree
        );
    }

    /**
     * Prepare test document response model.
     * @param testDocEntity the entity to be converted to a response model
     * @return a response model representing a test document entity
     */
    private UpdateStateTestDocResponseModel prepareTestDocResponseModel(
            TestDocument testDocEntity) {
        Map<String, UpdateStateSolutionDocResponseModel> solutionModels = new HashMap<>();
        for(SolutionDocument currentSolution : testDocEntity.getSolutions().values()) {
            solutionModels.put(
                    currentSolution.getId(),
                    prepareSolutionDocResponseModel(currentSolution)
            );
        }
        return new UpdateStateTestDocResponseModel(
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
     * Prepare a course response model.
     * @param courseEntity that will be converted to a course response model
     * @return a response model representing a course entity
     */
    private UpdateStateCourseResponseModel prepareCourseResponseModel(
            Course courseEntity) {
        Map<String, UpdateStateTestDocResponseModel> testModels = new HashMap<>();
        for(TestDocument currentTest : courseEntity.getTests().values()) {
            testModels.put(
                    currentTest.getId(),
                    prepareTestDocResponseModel(currentTest)
            );
        }
        return new UpdateStateCourseResponseModel(
                courseEntity.getId(),
                courseEntity.getCourseCode(),
                courseEntity.getCourseName(),
                testModels
        );
    }

    /**
     * Prepares the use case response model
     * @return UpdateStateResponseModel a structure of response models that represents the
     *      * current state
     */
    private UpdateStateResponseModel prepareResponseModel() {
        User currentUser = currentState.getCurrentUser();
        Map<String, UpdateStateCourseInfoResponseModel> courseInfoModels = new HashMap<>();
        Map<String, UpdateStateCourseResponseModel> usersCourseModels = new HashMap<>();

        Collection<CourseInfo> courseInfoItems =
                currentState.getAllCourseInfoItems().values();

        // Construct course info models
        for(CourseInfo courseInfoItem : courseInfoItems) {
            courseInfoModels.put(
                    courseInfoItem.getId(),
                    new UpdateStateCourseInfoResponseModel(
                            courseInfoItem.getId(),
                            courseInfoItem.getCourseCode(),
                            courseInfoItem.getCourseName()
                    )
            );
        }

        if (currentUser != null) {

            // Construct current user response model
            UpdateStateUserResponseModel currentUserModel =
                    new UpdateStateUserResponseModel(
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
            return new UpdateStateResponseModel(
                    currentUserModel,
                    usersCourseModels,
                    courseInfoModels
            );

        } else {
            // Construct response model
            return new UpdateStateResponseModel(
                    null,
                    new HashMap<>(),
                    new HashMap<>()
            );
        }

    }

    /** Updates the currentState state tracker entity such that it is in sync with the entities
     * stored in persistent storage.
     *
     * @return UpdateStateResponseModel a structure of response models that represents the
     * current state
     */
    @Override
    public UpdateStateResponseModel updateState() {

        Map<String, CourseInfo> allCourseInfoItems;
        User currentUser = currentState.getCurrentUser();

        // Check connection status
        if(dsGateway.getConnectionStatus()) {
            if (currentUser != null) {

                // Reload current user
                String currentUserId = currentState.getCurrentUser().getId();
                currentUser = constructUserById(currentUserId);
                currentState.setCurrentUser(currentUser);

                // Reload current user's course entities
                List<String> usersCourseIds = currentUser.getCourses();
                Course currentCourse;
                for (String usersCourseId : usersCourseIds) {
                    currentCourse = constructCourseById(usersCourseId);
                    currentState.addUpdateTrackedCourse(currentCourse);
                }

            }

            // Reload all course info items
            allCourseInfoItems = constructAllCourseInfoItems();
            currentState.setAllCourseInfoItems(allCourseInfoItems);

            // Construct response model
            UpdateStateResponseModel responseModel = prepareResponseModel();

            // Prepare success view
            presenter.prepareSuccessView(responseModel);

            return responseModel;
        } else {
            return presenter.prepareFailView("Database Connection Failed");
        }
    }

}
