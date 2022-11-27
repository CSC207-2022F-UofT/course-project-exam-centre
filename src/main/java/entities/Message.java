package entities;

// Entity layer


import java.time.LocalDateTime;

public interface Message {

    String getMessageId();

    String getSolutionId();

    String getUserId();

    String getParentId();

    String getBody();

    LocalDateTime getDate();
}
