


import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  * @author Daniel Dichte
 *  * @author Pascal Kießler
 *
 */
public class ImplServer implements MessageService{

    private int counterQueue;
    private Queue deliveryQueue;

    public ImplServer () {
        counterQueue = 1;
        deliveryQueue = new LinkedList();
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

        updateQueue(message);

        /* dient nur noch zu testzwecken */
        System.out.println(message);
    }

    /**
     * Update the message and add the message to the queue
     * in case the queue have 5 elements, die first message will be deleted and the new message will be added to the queue.
     * @param message A message from the client
     */
    private void updateQueue(String message) {
        Message newMessage = new Message (counterQueue, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis())), message);
        if (counterQueue <= 5 ) {
            deliveryQueue.add(newMessage);
        } else {
            deliveryQueue.remove();
            deliveryQueue.add(newMessage);
        }
    }

    private String builtMessages() {

        return null;
    }
}
