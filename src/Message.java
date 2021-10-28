/**
 * Message
 * @author Daniel Dichte
 * @author Pascal Kießler
 */
public class Message {

    private int messageID;
    private String timestamp;
    private String message;

    /**
     * Construktor
     * @param messageID number of the message
     * @param timestamp timestamp of the message
     * @param message The message
     */
    public Message (int messageID, String timestamp, String message) {
        this.messageID = messageID;
        this.timestamp = timestamp;
        this.message = message;
    }

    /**
     * Construktor without parameters
     */
    public Message() {    }

    /**
     * returns the ID of the message
     * @return ID of the message
     */
    public int getMessageID() {
        return messageID;
    }

    /**
     * get the timestamp of the message
     * @return timestamp of message
     */
    public String getTimestamp(){
        return timestamp;
    }

    /**
     * geht the message
     * @return get message
     */
    public String getMessage() {
        return message;
    }

    /**
     * set the message ID
     * @param messageID set message ID
     */
    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    /**
     * set the timestamp of the message
     * @param timestamp set timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * set the message
     * @param message set message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
