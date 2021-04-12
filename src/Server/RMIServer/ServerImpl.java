package Server.RMIServer;

import Client.networking.ClientModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl implements UpperCaseServer {

    public ServerImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public void toUpperCase(String str, ClientModel client) {
        String result = str.toUpperCase();
        // heavy calculation here

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ignored) {}

        try {
            client.uppercaseResult(result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
