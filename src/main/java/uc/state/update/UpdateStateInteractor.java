package uc.state.update;

import entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateStateInteractor implements UpdateStateInputBoundary {
    private final UpdateStateOutputBoundary presenter;
    private final UpdateStateDsGateway dsGateway;

    public UpdateStateInteractor(UpdateStateOutputBoundary presenter, UpdateStateDsGateway dsGateway) {
        this.presenter = presenter;
        this.dsGateway = dsGateway;
    }

    private User constructUserById(String userId) {
        List<String> rawUserData = dsGateway.getUserById(userId);
        List<List<String>> rawUserEnrolmentsData = dsGateway.getEnrolmentsByUserId(userId);

        String email = rawUserData.get(1);
        String firstName = rawUserData.get(2);
        String lastName = rawUserData.get(3);

        User newUser = UserFactory.create(firstName, lastName, email, userId);

        for (List<String> rawUserEnrolmentsDatum : rawUserEnrolmentsData) {
            newUser.addCourse(rawUserEnrolmentsDatum.get(2));
        }

        return newUser;
    }

    private CourseInfo constructCourseInfoItemById(String courseId) {
        List<String> rawCourseData = dsGateway.getCourseById(courseId);

        String courseCode = rawCourseData.get(1);
        String courseName = rawCourseData.get(2);

        return CourseFactory.create(courseName, courseCode, courseId);

    }

    private Course constructCourseById(String courseId) {
        List<String> rawCourseData = dsGateway.getCourseById(courseId);

        String courseCode = rawCourseData.get(1);
        String courseName = rawCourseData.get(2);

        Course newCourse = CourseFactory.create(courseName, courseCode, courseId);
        constructAllTestsByCourse(newCourse);

        return newCourse;

    }

    private void constructAllTestsByCourse(Course parentCourse) {
        String courseId = parentCourse.getId();
        List<List<String>> rawTestData = dsGateway.getAllTestDocsByCourseId(courseId);

        String currentTestName;
        String currentTestId;
        float currentTestEstimatedTime;
        int currentTestNumOfQuestions;
        String currentTestType;
        User currentTestUser;
        TestDocument currentTestDoc;

        for (int i = 0; i < rawTestData.size(); i++) {

            currentTestId = rawTestData.get(i).get(0);
            currentTestUser = constructUserById(rawTestData.get(i).get(1));
            currentTestType = rawTestData.get(i).get(3);
            currentTestNumOfQuestions = Integer.parseInt(rawTestData.get(i).get(4));
            currentTestEstimatedTime = Float.parseFloat(rawTestData.get(i).get(5));
            currentTestName = "Test #" + i;

            currentTestDoc = TestDocFactory.create(
                    currentTestName, currentTestId,
                    parentCourse, currentTestUser,
                    currentTestEstimatedTime,
                    currentTestNumOfQuestions,
                    currentTestType
            );

            constructSolutionsByTest(currentTestDoc);
            parentCourse.addTest(currentTestDoc);

        }
    }

    private void constructSolutionsByTest(TestDocument parentTestDoc) {
        String parentTestId = parentTestDoc.getId();
        List<List<String>> rawSolutionsData = dsGateway.getAllSolutionDocsByTestId(parentTestId);

        String currentSolutionName;
        String currentSolutionId;
        Course parentCourse = parentTestDoc.getCourse();
        User currentSolutionUser;
        float currentSolutionScore;
        float currentSolutionRecordedTime;
        String currentSolutionRootMessId;
        SolutionDocument currentSolutionDoc;

        List<SolutionDocument> testSolutions = new ArrayList<>();

        for (int i = 0; i < rawSolutionsData.size(); i++) {

            currentSolutionId = rawSolutionsData.get(i).get(0);
            currentSolutionUser = constructUserById(rawSolutionsData.get(i).get(2));
            currentSolutionScore = Float.parseFloat(rawSolutionsData.get(i).get(4));
            currentSolutionRecordedTime = Float.parseFloat(rawSolutionsData.get(i).get(5));
            currentSolutionRootMessId = rawSolutionsData.get(i).get(6);
            currentSolutionName = "Solution #" + i;

            currentSolutionDoc = SolutionDocFactory.create(
                    currentSolutionName,
                    currentSolutionId,
                    parentCourse,
                    currentSolutionUser,
                    currentSolutionScore,
                    parentTestDoc,
                    currentSolutionRecordedTime,
                    currentSolutionRootMessId
            );

            constructMessageTreeByParentId(
                    currentSolutionRootMessId,
                    currentSolutionDoc.getMessageTree()
            );

            parentTestDoc.addUpdateSolution(currentSolutionDoc);

        }
    }

    private void constructMessageTreeByParentId(
            String parentId, MessageTree newMessageTree) {
            List<List<String>> rawChildMessageData = dsGateway.getMessagesByParentId(parentId);

            String currMessId;
            String currMessSolutionId;
            String currMessUserId;
            String currMessBody;
            LocalDateTime currMessSentTimestamp;

        for (List<String> rawChildMessageDatum : rawChildMessageData) {

            currMessId = rawChildMessageDatum.get(0);
            currMessSolutionId = rawChildMessageDatum.get(1);
            currMessUserId = rawChildMessageDatum.get(2);
            currMessBody = rawChildMessageDatum.get(3);
            currMessSentTimestamp = LocalDateTime.parse(rawChildMessageDatum.get(4));

            newMessageTree.addMessage(
                    MessageFactory.create(
                        currMessId,
                        currMessSolutionId,
                        currMessUserId,
                        parentId,
                        currMessBody,
                        currMessSentTimestamp
                    )
            );
            constructMessageTreeByParentId(currMessId, newMessageTree);
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
    public UpdateStateResponseModel updateState(UpdateStateRequestModel requestModel) {

        StateTracker currentState = requestModel.getCurrentState();
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

        UpdateStateResponseModel responseModel =  new UpdateStateResponseModel(
                currentUser,
                usersCourses,
                allCourseInfoItems);

        return presenter.prepareSuccessView(responseModel);

    }

}
