import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  * @author Daniel Dichte
 *  * @author Pascal Kießler
 *
 */
public class ImplServer implements MessageService{

    private int counterQueue;

    public ImplServer () {
        counterQueue = 1;
    }

    @Override
    /**
     * Requests the next message for 'ClientID'.
     * @param clientID unique client ID
     * @return A message
     * @throws RemoteException
     */
    public String nextMessage(String clientID) throws RemoteException {
        return null;
    }

    @Override
    /**
     * Posts a new 'message' from 'clientID' into the chat.
     * @param clientID
     * @param message
     * @throws RemoteException
     */
    public void newMessage(String clientID, String message) throws RemoteException {
        Queue deliveryQueue = new LinkedList();
        deliveryQueue = unnamed (deliveryQueue, message);

        /* dient nur noch zu testzwecken */
        System.out.println(message);
    }

    /**
     *
     */
    public Queue unnamed (Queue deliveryQueue, String message) {
        String newMEssage = counterQueue + " : " + message + " - " +
        if (counterQueue <= 5 ) {
            deliveryQueue.add(message);
        } else {
            deliveryQueue.remove();
            deliveryQueue.add(message);
        }
    }
}
