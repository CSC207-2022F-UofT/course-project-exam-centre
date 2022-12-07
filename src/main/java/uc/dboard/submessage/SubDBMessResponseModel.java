package uc.dboard.submessage;

// Use case layer

import uc.state.update.responsemodels.UpdateStateCourseResponseModel;

import java.util.List;
import java.util.Map;

public class SubDBMessResponseModel {

    String messageBody;
    String creationTime;

    String parentId;

    Map<String, UpdateStateCourseResponseModel> currentCourses;


    public SubDBMessResponseModel(String messageBody, String creationTime, String parentId) {
        this.messageBody = messageBody;
        this.creationTime = creationTime;
        this.parentId = parentId;
    }

    public  String getMessageBody(){return messageBody;}
    public  String getCreationTime(){return creationTime;}


}
