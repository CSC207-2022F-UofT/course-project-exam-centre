package uc.dboard.submessage;

// Use case layer

import java.time.LocalDateTime;

public class SubDBMessResponseModel {

    String messageBody;
    String creationTime;

    String parentId;


    public SubDBMessResponseModel(String messageBody, String creationTime, String parentId) {
        this.messageBody = messageBody;
        this.creationTime = creationTime;
        this.parentId = parentId;
    }

    public  String getMessageBody(){return messageBody;}
    public  String getCreationTime(){return creationTime;}


}
