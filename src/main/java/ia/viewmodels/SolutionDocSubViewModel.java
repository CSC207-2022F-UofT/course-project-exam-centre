package ia.viewmodels;

public class SolutionDocSubViewModel {

    private final String solutionId;
    private final Integer voteTotal;
    private final Float recordedScore;
    private final Float estimatedTime;
    private final String solutionName;
    private final MessageTreeSubViewModel rootMessage;

    public SolutionDocSubViewModel(
            String solutionId,
            Integer voteTotal,
            Float recordedScore,
            Float estimatedTime,
            String solutionName,
            MessageTreeSubViewModel rootMessage) {

        this.solutionId = solutionId;
        this.voteTotal = voteTotal;
        this.recordedScore = recordedScore;
        this.estimatedTime = estimatedTime;
        this.solutionName = solutionName;
        this.rootMessage = rootMessage;
    }

    public String getSolutionId() {
        return this.solutionId;
    }

    public Integer getVoteTotal() {
        return this.voteTotal;
    }

    public Float getRecordedScore() {
        return this.recordedScore;
    }

    public Float getEstimatedTime() {
        return this.estimatedTime;
    }

    public MessageTreeSubViewModel getRootMessage() {
        return this.rootMessage;
    }

    public String getSolutionName() {
        return this.solutionName;
    }

}
