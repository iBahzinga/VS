import java.rmi.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
    private String servername;
    private MessageService stub;

    /**
     * Constructor
     */
    public Client () {
    }

    protected void init () {
        try {
            //Getting registry
            Registry registry = LocateRegistry.getRegistry(servername);

            //Looking up the registry for remote obj
            stub = (MessageService) registry.lookup("Hello");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Method to return the clientID
     * @return The clientID
     */
    public String getClientID() {
        return clientID;
    }

    /**
     * Method to return the Servername
     * @return The clientID
     */
    public String getServername() {
        return servername;
    }

    /**
     * Method to set the clientID.
     * @param clientID current clientID
     */
    protected void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * Method to set the Server.
     * @param servername current Server
     */
    protected void setServername(String servername) {
        this.servername = servername;
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