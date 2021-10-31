/**
 * Message
 * @author Daniel Dichte
 * @author Pascal Kießler
 */
public class ClientID {

    private String clientID;
    private int messageID[];
    private int gedaechnis;

    /**
     * Construktor
     * @param clientID number of the ClientID
     * @param messageID  of the ClientID
     * @param gedaechnis of the ClientID
     */
    public ClientID(String clientID ,int messageID, int gedaechnis) {
        this.clientID = clientID;
        this.messageID = new int[4];
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
    public int getMessageID(int i) {
        return messageID[i];
    }

    /**
     * get the gedaechnis of the ClientID
     * @return gedaechnis of ClientID
     */
    public int getGedaechnis(){
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

    public void setMessageID(int messageID) {
        this.messageID[] = messageID;
    }
    */

    /**
     * set the gedaechnis of the ClientID
     * @param gedaechnis set ClientID
     */
    public void setGedaechnis(int gedaechnis) {
        this.gedaechnis = gedaechnis;
    }
}
