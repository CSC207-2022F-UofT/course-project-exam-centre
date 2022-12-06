package uc.dboard.submessage;

// Use case layer

public class SubDBMessRequestModel {


    private final String solutionId;

    private final String userId;

    private final String parentId;

    private final String body;

    public SubDBMessRequestModel(
            String solutionId,
            String userId,
            String parentId,
            String body){

        this.solutionId = solutionId;
        this.userId = userId;
        this.parentId = parentId;
        this.body = body;
    }

    public String getSolutionId() {
        return solutionId;
    }

    public String getUserId() {
        return userId;
    }

    public String getParentId() {
        return parentId;
    }

    public String getBody() {
        return body;
    }
}
