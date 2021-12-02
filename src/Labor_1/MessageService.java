package Labor_1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *  * @author Daniel Dichte
 *  * @author Pascal Kießler
 *
 */
public interface MessageService extends Remote {

    /**
     * Requests the next message for 'ClientID'.
     * @param clientID unique client ID
     * @return A message
     * @throws RemoteException
     */
    public String nextMessage(String clientID) throws RemoteException;

    /**
     * Posts a new 'message' from 'clientID' into the chat.
     * @param clientID
     * @param message
     * @throws RemoteException
     */
    public void newMessage(String clientID, String message) throws RemoteException;
}