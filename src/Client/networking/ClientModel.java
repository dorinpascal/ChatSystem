package Client.networking;

import shared.Message;

import java.beans.PropertyChangeListener;

public interface ClientModel
{
  void sendMessage(Message text);
  void receiveMessage(Message message);
  void addPropertyChangeListener(String name, PropertyChangeListener listener);
  void deactivateClient();
}