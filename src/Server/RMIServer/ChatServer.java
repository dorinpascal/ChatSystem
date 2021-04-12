package Server.RMIServer;

import Client.RMIClient.ClientModel;
import shared.Message;
import shared.transferobjects.LogEntry;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote
{

  void connectedMessageFromClient(Message text) throws RemoteException;
  void disconnectionMessageFromClient(ClientModel dontBroadcastToMe)
      throws RemoteException;
  void normalMessageFromClient(Message result, ClientModel dontBroadcastToMe)
      throws RemoteException;
  void getUsersMessageFromClient(ClientModel dontBroadcastToMe) throws RemoteException;
  void registerClient(ClientModel clientToRegister) throws RemoteException;


}