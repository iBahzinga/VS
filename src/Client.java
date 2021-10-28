import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Client that can send and receive messages
 *
 *  * @author Daniel Dichte
 *  * @author Pascal Kießler
 *
 */
public class Client implements MessageService {
    private static String response;
    private String clientID;
    private MessageService stub;

    /**
     * Constructor
     */
    public Client () {
    }

    protected void send (String text) {
        try {
            //Getting registry
            Registry registry = LocateRegistry.getRegistry(null);

            //Looking up the registry for remote obj
            stub = (MessageService) registry.lookup("Hello");

            //Calling remote method
            newMessage(clientID, text);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public String receive () throws RemoteException {
        return nextMessage(clientID);
    }

    /**
     * MEthos to return the clientID
     * @return The clientID
     */
    public String getClientID() {
        return clientID;
    }

    /**
     * MEthod to set the clientID
     * @param clientID current clientID
     */
    protected void setClientID(String clientID) {
        this.clientID = clientID;
    }


    @Override
    /**
     * Requests the next message for 'ClientID'.
     * @param clientID unique client ID
     * @return A message
     * @throws RemoteException
     */
    public String nextMessage(String clientID) throws RemoteException {
        return stub.nextMessage(clientID);
    }

    @Override
    /**
     * Posts a new 'message' from 'clientID' into the chat.
     * @param clientID
     * @param message
     * @throws RemoteException
     */
    public void newMessage(String clientID, String message) throws RemoteException {
        stub.newMessage(clientID, message);
    }
}