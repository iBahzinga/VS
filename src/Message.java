/**
 * Message
 * @author Daniel Dichte
 * @author Pascal Kießler
 */
public class Message {

    private int messageID;
    private String timestamp;
    private String message;
    private String clientID;

    /**
     * Construktor
     * @param messageID number of the message
     * @param timestamp timestamp of the message
     * @param message The message
     */
    public Message (int messageID, String timestamp, String message, String clientID) {
        this.messageID = messageID;
        this.timestamp = timestamp;
        this.message = message;
        this.clientID = clientID;
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
     * returns the client ID who send the message
     * @return ID of the message
     */
    public String getclientID () {
        return clientID;
    }

    /**
     * set the client ID who send the message
     * @param clientID set timestamp
     */
    public void setclientID(String clientID) {
        this.clientID = clientID;
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
