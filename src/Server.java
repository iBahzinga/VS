import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.zip.ZipEntry;

/**
 *  * @author Daniel Dichte
 *  * @author Pascal Kießler
 *
 */
public class Server extends ImplServer {

    public Server() throws RemoteException{
        super();
    }

    /**
     * Main method to start the server
     * @param args
     */
    public static void main(String[] args){
        try {

            //Create the next message server
            ImplServer obj = new ImplServer();

            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub)
            MessageService stub = (MessageService) UnicastRemoteObject.exportObject(obj, 0);

            //Binding stub in registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Hello", stub);
            System.err.println("Server ready...");
        } catch(RemoteException e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }




}
