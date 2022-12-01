package uc.doc.submitsolution;

public class SubSDocDsRequestModel {

    private final String name;

    private final Float recordedScore;

    private final String userId;

    private final String courseId;

    private final String parentTestId;

    private final String rootMessageId;

    private final Integer voteTotal;

    private final String filePath;

    private final Float estimatedTime;

    public SubSDocDsRequestModel(
            String name,
            String userId,
            Float recordedScore,
            String courseId,
            String parentTestId,
            String filePath,
            String rootMessageId,
            Integer voteTotal,
            Float estimatedTime) {

        this.name = name;
        this.recordedScore = recordedScore;
        this.userId = userId;
        this.courseId = courseId;
        this.parentTestId = parentTestId;
        this.filePath = filePath;
        this.rootMessageId = rootMessageId;
        this.voteTotal = voteTotal;
        this.estimatedTime = estimatedTime;
    }

    public Float getRecordedScore() {
        return recordedScore;
    }

    public Float getEstimatedTime() {
        return estimatedTime;
    }

    public Integer getVoteTotal() {
        return voteTotal;
    }

    public String getRootMessageId() {
        return rootMessageId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getParentTestId() {
        return parentTestId;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }
}
