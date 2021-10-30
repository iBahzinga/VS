import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *  * @author Daniel Dichte
 *  * @author Pascal Kießler
 *
 */
public class ImplServer implements MessageService{

    private int counterQueue;
    //private Queue deliveryQueue;
    private LinkedList<Message> deliveryQueue;
    private String logPath;
    private Logger logger;
    private FileHandler fh;
    private SimpleFormatter formatter;

    /**
     *
     */
    public ImplServer () {
        counterQueue = 0;
        //deliveryQueue = new LinkedList();
        deliveryQueue = new LinkedList<>();
        String dir = System.getProperty("user.dir");
        logPath = dir;
        initLogger();
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
        createLogfile(clientID, null);
        if (!deliveryQueue.isEmpty()){
            for (int i = 0; i <= deliveryQueue.size() - 1; i++) {
                if (msg == null){
                    msg = builtMessages(i);
                } else {
                    msg = msg + "\n" + builtMessages(i);
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
        createLogfile(clientID, message);
        updateQueue(message, clientID);
        /* dient nur noch zu testzwecken */
        //System.out.println(message);
        test();
    }

    /**
     * built the next message to send it
     * @return the message containing the ClientID, messageID, the message and the time stamp
     */
    private String builtMessages(int count) {
        return deliveryQueue.get(count).getclientID() + "-" + deliveryQueue.get(count).getMessageID() + ":" + deliveryQueue.get(count).getMessage() + ", " + deliveryQueue.get(count).getTimestamp();
    }

    /**
     * Update the message and add the message to the queue
     * in case the queue have 5 elements, die first message will be deleted and the new message will be added to the queue.
     * @param message A message from the client
     */
    private void updateQueue(String message, String clientID) {
        Message newMessage = new Message (counterQueue, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis())), message, clientID);
        if (counterQueue <= 5 ) {
            deliveryQueue.add(newMessage);
            counterQueue++;
        } else {
            deliveryQueue.remove();
            deliveryQueue.add(newMessage);
            counterQueue++;
        }
    }


    /**
     * Record everything in the logfile
     * @param clientID ID of the current client
     * @param message message of the client
     */
    private void createLogfile (String clientID, String message) {
        if (clientID != null && message != null) {
            logger.info("Client: " + clientID + " hat die folgende Nachricht gesendet:\n" + message + "\n");
        } else {
            logger.info("Client: " + clientID + " hat eine Nachrichtenanfrage gestellt.\n");
        }
    }

    /**
     * Initialize the logger to create a logfile during the runtime
     */
    private void initLogger () {
        logger = Logger.getLogger("Logfile");
        try {
            fh = new FileHandler(logPath);
            logger.addHandler(fh);
            formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.setUseParentHandlers(false);
        logger.info("Server started");
    }




    /**
     * This test can be deleted soon
     */
    private void test () {
        for (int i = 0; i <= deliveryQueue.size()-1; i++) {
            System.out.println(deliveryQueue.get(i).getMessage());
        }
    }
}
