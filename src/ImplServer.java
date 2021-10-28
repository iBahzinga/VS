


import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 *  * @author Daniel Dichte
 *  * @author Pascal Kießler
 *
 */
public class ImplServer implements MessageService{

    private int counterQueue;
    //private Queue deliveryQueue;
    private LinkedList<Message> deliveryQueue;

    public ImplServer () {
        counterQueue = 0;
        //deliveryQueue = new LinkedList();
        deliveryQueue = new LinkedList<>();
    }

    @Override
    /**
     * Requests the next message for 'ClientID'.
     * @param clientID unique client ID
     * @return A message
     * @throws RemoteException
     */
    public String nextMessage(String clientID) throws RemoteException {
        String msg = null;
        if (!deliveryQueue.isEmpty()){
            for (int i = 0; i < deliveryQueue.size() - 1; i++) {
                if (msg == null){
                    msg = builtMessages(i, clientID);
                } else {
                    msg = msg + "\n" + builtMessages(i, clientID);
                }
            }
        }
        return msg;
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
        //System.out.println(message);
        test();
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
            counterQueue++;
        } else {
            deliveryQueue.remove();
            deliveryQueue.add(newMessage);
        }
    }

    /**
     * built the next message to send it
     * @return the message containing the ClientID, messageID, the message and the time stamp
     */
    private String builtMessages(int count, String clientID) {
        return clientID + "-" + deliveryQueue.get(count).getMessageID() + ":" + deliveryQueue.get(count).getMessage() + ", " + deliveryQueue.get(count).getTimestamp();
    }

    private void test () {
        for (int i = 0; i < deliveryQueue.size()-1; i++) {
            System.out.println(deliveryQueue.get(i).getMessage());
        }
    }
}
