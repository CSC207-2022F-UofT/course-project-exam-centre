package uc.state.update;

import entities.*;
import entities.factories.*;
import uc.state.update.dbmodels.*;
import uc.state.update.responsemodels.*;

import java.util.*;

public class UpdateStateInteractor implements UpdateStateInputBoundary {
    private final UpdateStateOutputBoundary presenter;
    private final UpdateStateDsGateway dsGateway;
    private final StateTracker currentState;

    private final UserFactory userFactory;
    private final TestDocFactory testDocFactory;
    private final CourseFactory courseFactory;
    private final SolutionDocFactory solutionDocFactory;
    private final MessageFactory messageFactory;

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

    private CourseInfo constructCourseInfoItemById(String courseId) {
        UpdateStateCourseDbModel courseData = dsGateway.getCourseById(courseId);

        return courseFactory.create(
                courseData.getCourseName(),
                courseData.getCourseCode(),
                courseData.getCourseId()
        );
    }

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

    private void constructMessageTreeByParentId(
            String parentId, MessageTree newMessageTree) {
            List<? extends UpdateStateMessageDbModel> rawChildMessageData = dsGateway.getMessagesByParentId(parentId);

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

    private UpdateStateMessageTreeResponseModel prepareMessageTreeResponseModel(
            MessageTree messageTree) {

        Message currentMessage = messageTree.getRootMessage();

        UpdateStateUserDbModel messageDbModel
                = dsGateway.getUserById(currentMessage.getUserId());

        List<UpdateStateMessageTreeResponseModel> replies = new ArrayList<>();

        UpdateStateUserResponseModel senderUserModel
                = new UpdateStateUserResponseModel(
                        messageDbModel.getUserId(),
                        messageDbModel.getEmail(),
                        messageDbModel.getFirstName(),
                        messageDbModel.getLastName()
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
