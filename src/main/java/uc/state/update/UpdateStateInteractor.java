package uc.state.update;

import entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateStateInteractor implements UpdateStateInputBoundary {
    private final UpdateStateOutputBoundary presenter;
    private final UpdateStateDsGateway dsGateway;
    private final StateTracker currentState;

    public UpdateStateInteractor(
            UpdateStateOutputBoundary presenter,
            UpdateStateDsGateway dsGateway,
            StateTracker stateTracker) {
        this.presenter = presenter;
        this.dsGateway = dsGateway;
        this.currentState = stateTracker;
    }

    private User constructUserById(String userId) {
        UpdateStateUserDbModel userData = dsGateway.getUserById(userId);
        List<String> rawUserEnrolmentsData = dsGateway.getCourseIdsByUserId(userId);

        User newUser = UserFactory.create(
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

        return CourseFactory.create(
                courseData.getCourseName(),
                courseData.getCourseCode(),
                courseData.getCourseId()
        );
    }

    private Course constructCourseById(String courseId) {
        UpdateStateCourseDbModel courseData = dsGateway.getCourseById(courseId);

        Course newCourse = CourseFactory.create(
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

            currentTestDoc = TestDocFactory.create(
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

            currentSolutionDoc = SolutionDocFactory.create(
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
                    MessageFactory.create(
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

    @Override
    public UpdateStateResponseModel updateState(
            UpdateStateRequestModel requestModel) {

        List<Course> usersCourses = new ArrayList<>();
        Map<String, CourseInfo> allCourseInfoItems;
        User currentUser = currentState.getCurrentUser();

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
                usersCourses.add(currentCourse);
            }

        }

        // Reload all course info items
        allCourseInfoItems = constructAllCourseInfoItems();
        currentState.setAllCourseInfoItems(allCourseInfoItems);

        return new UpdateStateResponseModel(
                currentUser,
                usersCourses,
                allCourseInfoItems);

    }

}
