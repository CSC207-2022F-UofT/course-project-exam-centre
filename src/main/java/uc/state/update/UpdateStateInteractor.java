package uc.state.update;

import entities.*;
import entities.factories.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** UpdateStateInteractor implements the ability to update the state tracker.
 * @layer use cases
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

    /** Constructs an instance of UpdateStateInteractor containing a presenter, dsGateway, stateTracker and
     * the different factories for creating entities.
     *
     * @param presenter provides methods to output data
     * @param dsGateway provides methods to access and update persistent data
     * @param stateTracker the stateTracker we are updating
     * @param userFactory provides methods to create User entities
     * @param courseFactory provides methods to create Course entities
     * @param testDocFactory provides methods to create TestDocument entities
     * @param solutionDocFactory provides methods to create SolutionDocument entities
     * @param messageFactory provides methods to create Message entities
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

    /** Constructs a User using a userId
     *
     * @param userId the userId of the user being created
     * @return the created User entity
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

    /** Constructs CourseInfo using a courseId
     *
     * @param courseId the courseId of the CourseInfo being created
     * @return the created CourseInfo
     */
    private CourseInfo constructCourseInfoItemById(String courseId) {
        UpdateStateCourseDbModel courseData = dsGateway.getCourseById(courseId);

        return courseFactory.create(
                courseData.getCourseName(),
                courseData.getCourseCode(),
                courseData.getCourseId()
        );
    }

    /** Constructs a Course entity using a courseId
     *
     * @param courseId the courseId of the Course being created
     * @return the created Course
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

    /** Constructs all the tests belonging to a course
     *
     * @param parentCourse The course that all the tests belong to
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

    /** Constructs all the solutions belonging to a test
     *
     * @param parentTestDoc the test that has all the solutions
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

    /** Constructs a messageTree using the parentId of a message
     *
     * @param parentId the parentId of a message
     * @param newMessageTree the message tree being added
     */
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

    /** Constructs all the courseInfo items
     *
     * @return a mapping of courseId to the courseInfo
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

    /** Updates the state to contain all the new data being created.
     *
     * @param requestModel requestModel containing information about the current state
     * @return A responseModel corresponding to the requestModel
     */
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
