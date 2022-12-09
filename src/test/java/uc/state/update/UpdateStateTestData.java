package uc.state.update;

import entities.factories.*;
import uc.state.update.dbmodels.*;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateStateTestData {
    private static UserFactory userFactory;
    private static CourseFactory courseFactory;
    private static TestDocFactory testFactory;
    private static SolutionDocFactory solutionFactory;
    private static MessageFactory messageFactory;
    private static UpdateStateUserDbModel userDbResponseModel;
    private static UpdateStateCourseDbModel courseDbResponseModel;
    private static UpdateStateTestDocDbModel testDbResponseModel;
    private static UpdateStateSolutionDocDbModel solutionDbResponseModel;
    private static UpdateStateMessageDbModel messageDbResponseModel;
    private static List<String> storedAllCourseIds;
    private static List<String> storedUsersCourseIds;

    public UpdateStateTestData() {
        userFactory = new UserFactory();
        courseFactory = new CourseFactory();
        testFactory =  new TestDocFactory();
        solutionFactory = new SolutionDocFactory();
        messageFactory = new MessageFactory();

        userDbResponseModel = new UpdateStateUserDbModel() {
            @Override
            public String getUserId() {
                return null;
            }

            @Override
            public String getFirstName() {
                return null;
            }

            @Override
            public String getLastName() {
                return null;
            }

            @Override
            public String getEmail() {
                return null;
            }
        };

        courseDbResponseModel = new UpdateStateCourseDbModel() {
            @Override
            public String getCourseId() {
                return null;
            }

            @Override
            public String getCourseCode() {
                return null;
            }

            @Override
            public String getCourseName() {
                return null;
            }
        };

        testDbResponseModel = new UpdateStateTestDocDbModel() {
            @Override
            public String getTestId() {
                return null;
            }

            @Override
            public String getUserId() {
                return null;
            }

            @Override
            public String getTestType() {
                return null;
            }

            @Override
            public Integer getNumOfQuestions() {
                return null;
            }

            @Override
            public Float getEstimatedTime() {
                return null;
            }

            @Override
            public String getTestName() {
                return null;
            }
        };

        solutionDbResponseModel = new UpdateStateSolutionDocDbModel() {
            @Override
            public String getSolutionId() {
                return null;
            }

            @Override
            public String getSolutionName() {
                return null;
            }

            @Override
            public String getTestId() {
                return null;
            }

            @Override
            public String getUserId() {
                return null;
            }

            @Override
            public Integer getVoteTotal() {
                return null;
            }

            @Override
            public Float getRecordedScore() {
                return null;
            }

            @Override
            public Float getEstimatedTime() {
                return null;
            }

            @Override
            public String getRootMessageId() {
                return null;
            }
        };

        messageDbResponseModel = new UpdateStateMessageDbModel() {
            @Override
            public String getMessageId() {
                return null;
            }

            @Override
            public String getSolutionId() {
                return null;
            }

            @Override
            public String getUserId() {
                return null;
            }

            @Override
            public String getParentId() {
                return null;
            }

            @Override
            public String getMessageBody() {
                return null;
            }

            @Override
            public LocalDateTime getMessageSentTimestamp() {
                return null;
            }
        };
    }
}
