import java.rmi.RemoteException;

public class ImplServer implements MessageService{

    @Override
    public void newMessage(/*String clientID, */String message) throws RemoteException {
        System.out.println(message);


    }
}
