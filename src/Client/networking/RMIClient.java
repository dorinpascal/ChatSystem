package Client.networking;

import Client.RMIClient.ClientModel;
import Server.server.Server;
import shared.transferobjects.LogEntry;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIClient implements ClientModel
{
  private Socket socket;
  private boolean running = true;
  private String name;
  private PropertyChangeSupport support;
  private  Server server;

  /** Client constructor, requires a name/username for client */
  public RMIClient(String name)
  {
    this.name = name;
    support = new PropertyChangeSupport(this);


  }

  @Override
  public void startClient() {

    try {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (Server) registry.lookup("ChatServer");
      //server.registerClient(this);
    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }
  }


  @Override
  public List<LogEntry> getLog() {
    try {
      return server.getLogs();
    } catch (RemoteException e) {
      throw new RuntimeException("Could not contact server");
    }

  }


  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }
/*

  */
/** Sends a property change to listeners to check if there are any unread messages *//*

  @Override public void receiveMessage(Message message)
  {
    System.out.println("Client receive" + message);
    support.firePropertyChange("NewMessage", null, message);
  }
*/

 /* *//** Close the client-server connection from the client side *//*
  @Override public void deactivateClient()
  {
    running = false;
    try
    {
      objectOutputStream.close();
      socket.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }*/



  /** Check if client is still running/online  */
  public boolean isRunning()
  {
    return running;
  }
}
