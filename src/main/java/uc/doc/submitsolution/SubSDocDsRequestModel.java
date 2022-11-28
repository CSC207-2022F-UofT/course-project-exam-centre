package uc.doc.submitsolution;

public class SubSDocDsRequestModel {

    private String id;

    private Integer recordedScore;

    private String userID;

    public SubSDocDsRequestModel(String id, Integer recordedScore, String userID) {
        this.id = id;
        this.recordedScore = recordedScore;
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public Integer getRecordedScore() {
        return recordedScore;
    }

    public String getUserID() {
        return userID;
    }
}
