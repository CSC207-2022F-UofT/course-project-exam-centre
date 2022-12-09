package uc.state.update;

import uc.state.update.dbmodels.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateStateTestData {
    private Map<String, UpdateStateUserDbModel> storedUsers; // retrieved via userId
    private Map<String, UpdateStateCourseDbModel> storedCourses; // retrieved via courseId
    private Map<String, List<UpdateStateTestDocDbModel>> storedTests; // retrieved via courseId
    private Map<String, List<UpdateStateSolutionDocDbModel>> storedSolutions; // retrieved via testId
    private Map<String, List<UpdateStateMessageDbModel>> storedMessages; // retrieved via parentId
    private List<String> storedAllCourseIds;
    private Map<String, List<String>> storedCourseIdsByUserId;

    public UpdateStateTestData() {
        createStoredUsers();
        createStoredCourses();
        createStoredTests();
        createStoredSolutions();
        createdStoredMessages();
        createStoredAllCourseIds();
        createStoredCourseIdsByUserId();
    }

    private void createStoredUsers() {
        storedUsers = new HashMap<>();
        UpdateStateUserDbModel storedUser1 = new UpdateStateUserDbModel() {
            @Override
            public String getUserId() {
                return "user1Id";
            }

            @Override
            public String getFirstName() {
                return "User";
            }

            @Override
            public String getLastName() {
                return "One";
            }

            @Override
            public String getEmail() {
                return "user.one@mail.utoronto.ca";
            }
        };

        UpdateStateUserDbModel storedUser2 = new UpdateStateUserDbModel() {
            @Override
            public String getUserId() {
                return "user2Id";
            }

            @Override
            public String getFirstName() {
                return "User";
            }

            @Override
            public String getLastName() {
                return "Two";
            }

            @Override
            public String getEmail() {
                return "user.two@mail.utoronto.ca";
            }
        };
    storedUsers.put(storedUser1.getUserId(), storedUser1);
    storedUsers.put(storedUser2.getUserId(), storedUser2);
    }

    private void createStoredCourses() {
        storedCourses = new HashMap<>();
        UpdateStateCourseDbModel csc207 = new UpdateStateCourseDbModel() {
            @Override
            public String getCourseId() {
                return "csc207Id";
            }

            @Override
            public String getCourseCode() {
                return "CSC207";
            }

            @Override
            public String getCourseName() {
                return "Software Design";
            }
        };
        UpdateStateCourseDbModel mat137 = new UpdateStateCourseDbModel() {
            @Override
            public String getCourseId() {
                return "mat137Id";
            }

            @Override
            public String getCourseCode() {
                return "MAT137";
            }

            @Override
            public String getCourseName() {
                return "Calculus with Anxiety";
            }
        };
        storedCourses.put(csc207.getCourseId(), csc207);
        storedCourses.put(mat137.getCourseId(), mat137);
    }

    private void createStoredTests() {
        storedTests = new HashMap<>();

        List<UpdateStateTestDocDbModel> csc207Tests = new ArrayList<>();
        UpdateStateTestDocDbModel csc207Test = new UpdateStateTestDocDbModel() {
            @Override
            public String getTestId() {
                return "csc207TestId";
            }

            @Override
            public String getUserId() {
                return "";
            }

            @Override
            public String getTestType() {
                return "";
            }

            @Override
            public Integer getNumOfQuestions() {
                return 0;
            }

            @Override
            public Float getEstimatedTime() {
                return 0.0F;
            }

            @Override
            public String getTestName() {
                return "";
            }
        };

        csc207Tests.add(csc207Test);
        storedTests.put("csc207Id", csc207Tests);

        List<UpdateStateTestDocDbModel> mat137Tests = new ArrayList<>();
        UpdateStateTestDocDbModel mat137Test = new UpdateStateTestDocDbModel() {
            @Override
            public String getTestId() {
                return "mat137TestId";
            }

            @Override
            public String getUserId() {
                return "";
            }

            @Override
            public String getTestType() {
                return "";
            }

            @Override
            public Integer getNumOfQuestions() {
                return 0;
            }

            @Override
            public Float getEstimatedTime() {
                return 0.0F;
            }

            @Override
            public String getTestName() {
                return "";
            }
        };
        mat137Tests.add(mat137Test);
        storedTests.put("mat137Id", csc207Tests);
    }

    private void createStoredSolutions() {
        storedSolutions = new HashMap<>();
        List<UpdateStateSolutionDocDbModel> csc207TestSolutions = new ArrayList<>();
        UpdateStateSolutionDocDbModel csc207TestSolution = new UpdateStateSolutionDocDbModel() {
            @Override
            public String getSolutionId() {
                return "csc207SolutionTestId";
            }

            @Override
            public String getSolutionName() {
                return "";
            }

            @Override
            public String getTestId() {
                return "csc207TestId";
            }

            @Override
            public String getUserId() {
                return "";
            }

            @Override
            public Integer getVoteTotal() {
                return 0;
            }

            @Override
            public Float getRecordedScore() {
                return 0.0F;
            }

            @Override
            public Float getEstimatedTime() {
                return 0.0F;
            }

            @Override
            public String getRootMessageId() {
                return "";
            }
        };
        csc207TestSolutions.add(csc207TestSolution);
        storedSolutions.put(csc207TestSolution.getTestId(), csc207TestSolutions);
    }

    private void createdStoredMessages() {
        storedMessages = new HashMap<>();
        List<UpdateStateMessageDbModel> csc207SolutionMessages = new ArrayList<>();

        UpdateStateMessageDbModel csc207SolutionMessage = new UpdateStateMessageDbModel() {
            @Override
            public String getMessageId() {
                return "";
            }

            @Override
            public String getSolutionId() {
                return "";
            }

            @Override
            public String getUserId() {
                return "";
            }

            @Override
            public String getParentId() {
                return "parentId";
            }

            @Override
            public String getMessageBody() {
                return "csc207 rocks!";
            }

            @Override
            public LocalDateTime getMessageSentTimestamp() {
                return LocalDateTime.now();
            }
        };
        csc207SolutionMessages.add(csc207SolutionMessage);
        storedMessages.put(csc207SolutionMessage.getSolutionId(), csc207SolutionMessages);
    }

    private void createStoredAllCourseIds() {
        storedAllCourseIds = new ArrayList<>();
        storedAllCourseIds.add("csc207Id");
        storedAllCourseIds.add("mat207Id");
    }

    private void createStoredCourseIdsByUserId(){
        storedCourseIdsByUserId = new HashMap<>();
        // user 1 is enrolled in csc207
        // user 2 is enrolled in mat137
        List<String> user1Courses = new ArrayList<>();
        user1Courses.add("csc207Id");

        List<String> user2Courses = new ArrayList<>();
        user2Courses.add("mat137Id");

        storedCourseIdsByUserId.put("user1Id", user1Courses);
        storedCourseIdsByUserId.put("user2Id", user2Courses);
    }

    public UpdateStateUserDbModel getUserDbResponseModelById(String userId) {
        return storedUsers.get(userId);
    }

    public UpdateStateCourseDbModel getCourseDbResponseModelById(String courseId) {
        return storedCourses.get(courseId);
    }

    public List<UpdateStateTestDocDbModel> getStoredTestsByCourseId(String courseId) {
        return storedTests.get(courseId);
    }

    public List<UpdateStateSolutionDocDbModel> getStoredSolutionsByTestId(String testId) {
        return storedSolutions.get(testId);
    }

    public List<UpdateStateMessageDbModel> getStoredMessagesByParentId(String parentId) {
        return storedMessages.get(parentId);
    }

    public List<String> getStoredAllCourseIds(){
        return storedAllCourseIds;
    }

    public List<String> getStoredCourseIdsByUserId(String userId){
        return storedCourseIdsByUserId.get(userId);
    }
}
