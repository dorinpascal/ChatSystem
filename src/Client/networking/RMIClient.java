package Client.networking;

import Client.RMIClient.ClientModel;
import Server.RMIServer.ChatServer;
import Server.RMIServer.ServerImpl;
import shared.Message;
import shared.transferobjects.LogEntry;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient implements ClientModel
{
  private boolean running = true;
  private String name;
  private PropertyChangeSupport support;
  private ChatServer server;

  /** Client constructor, requires a name/username for client */
  public RMIClient(String name) throws RemoteException
  {
    this.name = name;
    UnicastRemoteObject.exportObject(this, 0);
    support = new PropertyChangeSupport(this);
    System.out.println(11);
  }

  @Override public void startClient() throws RemoteException
  {
    System.out.println(22);
    Registry registry = LocateRegistry.getRegistry("localhost", 1099);
    try
    {
      server = (ChatServer) registry.lookup("ChatServer");
    }
    catch (NotBoundException e)
    {
      e.printStackTrace();
    }
    server.registerClient(this);
  }

  /** Send a Message object from client to server */
  @Override public void sendMessage(Message text)
  {
    try
    {
      System.out.println(44);
      server.normalMessageFromClient(text, this);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void update(LogEntry log) throws RemoteException
  {

  }

  @Override public String getUser()
  {
    return name;
  }

  /** Sends a property change to listeners to check if there are any unread messages */
  @Override public void receiveMessage(Message message)
  {
    System.out.println(55);
    System.out.println("Client receive" + message);
    support.firePropertyChange("NewMessage", null, message);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void deactivateClient()
  {

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

  /** Check if client is still running/online */
  public boolean isRunning()
  {
    return running;
  }
}
