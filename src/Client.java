import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client /*implements MessageService*/ {
    private static String response;
    private String clientID;
    private String serverP;

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
            MessageService stub = (MessageService) registry.lookup("Hello");

            //Calling remote method
            stub.newMessage(text);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public String getClientID() {
        return clientID;
    }

    public String getServerP() {
        return serverP;
    }

    protected void setClientID(String clientID) {
        this.clientID = clientID;
    }

    protected void setServerP(String serverP) { this.serverP = serverP; }
}