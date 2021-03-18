package Client1.Networking;

import Client1.MODEL.DataModelManager;
import Shared.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements ClientModel
{

  private Socket socket;
  private ObjectOutputStream objectOutputStream;
  private boolean running = true;
  private String name;
  private PropertyChangeSupport support;

  public Client(String name)
  {
    this.name = name;
    support = new PropertyChangeSupport(this);

    try
    {
      this.socket = new Socket("localhost", 2910);
      this.objectOutputStream = new ObjectOutputStream(
          socket.getOutputStream());
      ClientSocketHandler clientSocketHandler = new ClientSocketHandler(socket,
          this);

      Thread thread = new Thread(clientSocketHandler);
      thread.setDaemon(true);
      thread.start();
    }
    catch (IOException e)
    {
      throw new RuntimeException("Unable to connect to the server");
    }
    sendMessage(new Message(name, "Listener", true));
    System.out.println("coooo");
    sendMessage(new Message(name, "Null", true));
    System.out.println("nieeee");

  }

  @Override public void sendMessage(Message text)
  {
    try
    {
      objectOutputStream.writeObject(text);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void receiveMessage(Message message)
  {
    System.out.println("Client receive" + message);
    support.firePropertyChange("NewMessage", null, message);
  }

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
  }

  public boolean isRunning()
  {
    return running;
  }
}
