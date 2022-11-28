package usecases.dboard.submessage;

// Use case layer

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
