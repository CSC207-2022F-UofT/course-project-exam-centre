package entities;

// Entity layer

public interface Message {

    int getMessageId();

    int getSolutionId();

    int getUserId();

    int getParentId();

    String getBody();
}
