package Client1.Networking;

import Client1.MODEL.DataModelManager;
import Client1.ViewModel.ViewModelLogin;
import Shared.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.Socket;

public class Client implements ClientModel, PropertyChangeListener
{

    private String hostname = "localhost";
    private Socket socket;
    private boolean running = true;
    private DataModelManager manager;
    private String name;


    public Client(DataModelManager manager, String name) {
        this.manager = manager;
        this.name=name;

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override public void sendMsg(Message text)
    {

    }
}
