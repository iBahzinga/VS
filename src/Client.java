import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client /*implements MessageService*/ {
    private static String response;

    /**
     * Constructor
     */
    public Client () {

    }

    /**
     * Main method.
     * @param args number of args given
     */
    public static void main(String[] args){
        try {
            //Getting registry
            Registry registry = LocateRegistry.getRegistry(null);

            //Looking up the registry for remote obj
            MessageService stub = (MessageService) registry.lookup("Hello");

            //Calling remote method
            stub.newMessage();

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}