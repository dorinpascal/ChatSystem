package Client.model;

import shared.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface DataModel
{
  void sendMessage(Message text);
  void update(PropertyChangeEvent evt);
  void createClient(String name);
  void addPropertyChangeListener(String name, PropertyChangeListener listener);
  void deactivateClient();
  String getUsername();
  void getUsers();
}
