package uc.state.update;

import entities.*;

import java.util.ArrayList;
import java.util.List;

public class UpdateStateInteractor implements UpdateStateInputBoundary {
    private UpdateStateOutputBoundary presenter;
    private UpdateStateDsGateway dsGateway;

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

    private Course constructCourseInfoItemById(String courseId) {
        List<String> rawCourseData = dsGateway.getCourseById(courseId);

        String courseCode = rawCourseData.get(1);
        String courseName = rawCourseData.get(2);

        Course newCourse = null; // TODO: Implement Course entities
        // Course newCourse = CourseFactory.create(courseName, courseCode, courseId);


        return newCourse;

    }

    private Course constructCompleteCourseById(String courseId) {
        List<String> rawCourseData = dsGateway.getCourseById(courseId);

        String courseCode = rawCourseData.get(1);
        String courseName = rawCourseData.get(2);

        Course newCourse = null; // TODO: Implement Course entities
        // Course newCourse = CourseFactory.create(courseName, courseCode, courseId);


        return newCourse;

    }

    private List<TestDocument> constructAllTestsByCourse(Course parentCourse) {
        String courseId = parentCourse.getCourseId();
        List<List<String>> rawTestData = dsGateway.getAllTestDocsByCourseId(courseId);

        String currentTestName;
        String currentTestId;
        float currentTestEstimatedTime;
        int currentTestNumOfQuestions;
        String currentTestType;
        User currentTestUser;
        TestDocument currentTestDoc;
        List<SolutionDocument> currentTestSolutions;

        List<TestDocument> courseTests = new ArrayList<>();

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

            currentTestSolutions = constructSolutionsByTest(currentTestDoc);

            for (SolutionDocument currentTestSolution : currentTestSolutions) {
                currentTestDoc.addUpdateSolution(currentTestSolution);
            }

            courseTests.add(currentTestDoc);

        }
        return courseTests;
    }

    private List<SolutionDocument> constructSolutionsByTest(TestDocument parentTestDoc) {
        String parentTestId = parentTestDoc.getId();
        List<List<String>> rawSolutionsData = dsGateway.getAllSolutionDocsByTestId(parentTestId);

        String currentSolutionName;
        String currentSolutionId;
        Course parentCourse = parentTestDoc.getCourse();
        User currentSolutionUser;
        int currentSolutionScore;
        float currentSolutionRecordedTime;
        String currentSolutionRootMessId;
        SolutionDocument currentSolutionDoc;

        List<SolutionDocument> testSolutions = new ArrayList<>();

        for (int i = 0; i < rawSolutionsData.size(); i++) {

            currentSolutionId = rawSolutionsData.get(i).get(0);
            currentSolutionUser = constructUserById(rawSolutionsData.get(i).get(2));
            currentSolutionScore = Integer.parseInt(rawSolutionsData.get(i).get(4));
            currentSolutionRecordedTime = Float.parseFloat(rawSolutionsData.get(i).get(5));
            currentSolutionRootMessId = rawSolutionsData.get(i).get(6); // TODO: Fix solution entity re: discussion board
            currentSolutionName = "Test #" + i;

            currentSolutionDoc = SolutionDocFactory.create(
                    currentSolutionName,
                    currentSolutionId,
                    parentCourse,
                    currentSolutionUser,
                    currentSolutionScore,
                    parentTestDoc,
                    currentSolutionRecordedTime
            );

            // TODO: Load discussion board

            testSolutions.add(currentSolutionDoc);

        }
        return testSolutions;

    }

    private List<Course> constructAllCourseInfoItems() {
        List<String> rawCourseIdsData = dsGateway.getAllCourseIds();
        List<Course> allCourseItems = new ArrayList<>();

        for (String rawCourseIdsDatum : rawCourseIdsData) {
            allCourseItems.add(
                    constructCourseInfoItemById(rawCourseIdsDatum)
            );
        }
        return allCourseItems;
    }

    @Override
    public UpdateStateResponseModel updateState(UpdateStateRequestModel requestModel) {

        StateTracker currentState = requestModel.getCurrentState();
        List<Course> usersCourses = new ArrayList<>();
        List<Course> allCourseInfoItems;
        User currentUser = currentState.getCurrentUser();

        if (currentUser != null) {

            // Reload current user
            String currentUserId = currentState.getCurrentUser().getUserId();
            currentUser = constructUserById(currentUserId);
            currentState.setCurrentUser(currentUser);

            // Reload current user's course entities
            List<String> usersCourseIds = currentUser.getCourses();
            Course currentCourse;
            for (String usersCourseId : usersCourseIds) {
                currentCourse = constructCompleteCourseById(usersCourseId);
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
