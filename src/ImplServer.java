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

    private final int MEMORYTIME = 300000; // 5 Minuten
    private int counterQueue;
    private int countCID;
    //private Queue deliveryQueue;
    private LinkedList<Message> deliveryQueue;
    private String logPath;
    private Logger logger;
    private FileHandler fh;
    private SimpleFormatter formatter;
    private ClientID clienten[];

    /**
     *
     */
    public ImplServer () {
        counterQueue = 0;
        countCID = 0;
        //deliveryQueue = new LinkedList();
        deliveryQueue = new LinkedList<>();
        logPath = System.getProperty("user.dir");
        initLogger();
        clienten = new ClientID[10];
        Thread newThread = new Thread(() -> {
            while(true) {
                threadControl();
                updateClients();
            }
        });
        newThread.start();
    }

    /**
     * Controls the memorytime
     */
    private void threadControl(){
        for(int i = 0; i < clienten.length; i++){
            if (clienten[i] != null){
                if(clienten[i].getGedaechnis() < System.currentTimeMillis()){
                    clienten[i] = null;
                    countCID--;
                    /*
                    for (int x = 0; x < clienten.length; x++) {
                        if (clienten[x] == null) {
                            System.out.println(x + ": null");
                        } else {
                            System.out.println(x + ": " + clienten[x].getclientID());
                        }
                    }
                    */
                }
            }
        }
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
        boolean known = false;
        for (int i = 0; i < clienten.length; i++) {
            if (clienten[i] != null){
                if (clientID.equals(clienten[i].getclientID())) {
                    clienten[i].setGedaechnis(System.currentTimeMillis() + MEMORYTIME); //+ 5 Minuten
                    if (!deliveryQueue.isEmpty()){

                        /* durchiterieren der delivery queue */
                        for (int j = 0; j <= deliveryQueue.size() - 1; j++) {

                            /* Check if the messageID of the client is < as the messageID of the message in the delivery queue  */
                            if (clienten[i].getMessageID() < deliveryQueue.get(j).getMessageID()) {
                                if (msg == null){
                                    msg = builtMessages(j);
                                } else {
                                    msg = msg + "\n" + builtMessages(j);
                                }
                            }
                        }
                        clienten[i].setMessageID(deliveryQueue.get(deliveryQueue.size()-1).getMessageID());
                    }
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
        for (int i = 0; i < clienten.length; i++) {
            if (clienten[i] == null){
                 clienten[i] = new ClientID(clientID, counterQueue, System.currentTimeMillis() + MEMORYTIME); // + 5 Minuten
                 countCID++;
                 break;
             } else if (clientID.equals(clienten[i].getclientID())){
                 updateQueue(message, clientID);
                 break;
             }
        }
        /* dient nur noch zu testzwecken */
        //System.out.println(message);
        test();
    }

    /**
     * Updates our Client Array in the correct order
     */
    private void updateClients(){
        for(int i = 0; i < countCID; i++){
            if(clienten[i] == null){
                for(int j = clienten.length -1; j > i; j--){
                    if(clienten[j] != null){
                        clienten[i] = clienten[j];
                        clienten[j] = null;
                        break;
                    }
                }
            }
        }
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
        Message newMessage = new Message (counterQueue +1, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis())), message, clientID);
        if (counterQueue < 5 ) {
            deliveryQueue.add(newMessage);
        } else {
            deliveryQueue.remove();
            deliveryQueue.add(newMessage);
        }
        counterQueue++;
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
