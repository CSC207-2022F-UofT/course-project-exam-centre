package entities;

// Entity layer

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Message {

    int getMessageId();

    int getSolutionId();

    int getUserId();

    int getParentId();

    String getBody();
}
