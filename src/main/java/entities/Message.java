package entities;

// Entity layer


import java.time.LocalDateTime;
/** Message Interface that provides basic attributes all documents must have.
 * @layer entities
 */
public interface Message {
    /**
     * Gets the Message Id of this message.
     * @return Returns the String of the Message Id for this message.
     */
    String getMessageId();
    /**
     * Gets the Solution Id of this message
     * @return Returns the string of the Solution Id for this message.
     */
    String getSolutionId();
    /**
     * Gets the User Id of this message
     * @return Returns String of the User id for this message.
     */
    String getUserId();
    /**
     * Gets the Parent Id of this message
     * @return Returns String of the Parent id for this message.
     */
    String getParentId();
    /**
     * Gets the Message Body of this message
     * @return Returns String of the Message Body for this message.
     */
    String getBody();

    /**
     * Gets the Creation Time of this message
     * @return Returns LocalDateTime Object of the time this message was created.
     */
    LocalDateTime getDate();
}
