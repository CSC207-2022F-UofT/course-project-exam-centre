package uc.state.update;

import java.time.LocalDateTime;

/** UpdateStateMessageDbModel provides methods to TODO: finish
 * @layer use cases
 */
public interface UpdateStateMessageDbModel {
    /**
     * TODO: Finish
     * @return a string containing the messageId
     */
    String getMessageId();
    /**
     * TODO: Finish
     * @return a string containing the solutionId
     */
    String getSolutionId();
    /**
     * TODO: Finish
     * @return a string containing the userId
     */
    String getUserId();
    /**
     * TODO: Finish
     * @return a string containing the message's parentId
     */
    String getParentId();
    /**
     * TODO: Finish
     * @return a string containing the message's body
     */
    String getMessageBody();
    /**
     * TODO: Finish
     * @return the time corresponding to when the message was sent
     */
    LocalDateTime getMessageSentTimestamp();
}
