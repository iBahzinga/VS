package Labor_1;

/**
 * Message
 * @author Daniel Dichte
 * @author Pascal Kießler
 */
public class ClientID {

    private String clientID;
    private int messageID;
    private long gedaechnis;

    /**
     * Construktor
     * @param clientID number of the ClientID
     * @param messageID  of the ClientID
     * @param gedaechnis of the ClientID
     */
    public ClientID(String clientID ,int messageID, long gedaechnis) {
        this.clientID = clientID;
        this.messageID = messageID;
        this.gedaechnis = gedaechnis;
    }

    /**
     * Construktor without parameters
     */
    public ClientID() {    }

    /**
     * returns the client ID who send the ClientID
     * @return ID of the ClientID
     */
    public String getclientID () {
        return clientID;
    }

    /**
     * returns the ID of the ClientID
     * @return ID of the ClientID
     */
    public int getMessageID() {
        return messageID;
    }

    /**
     * get the gedaechnis of the ClientID
     * @return gedaechnis of ClientID
     */
    public long getGedaechnis(){
        return gedaechnis;
    }

    /**
     * set the client ID who send the message
     * @param clientID set timestamp
     */
    public void setclientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * set the message ID
     * @param messageID set message ID
    */
    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    /**
     * set the gedaechnis of the ClientID
     * @param gedaechnis set ClientID
     */
    public void setGedaechnis(long gedaechnis) {
        this.gedaechnis = gedaechnis;
    }
}
