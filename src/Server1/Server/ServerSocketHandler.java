package Server1.Server;

import Server1.Domain.Message;

import java.io.*;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{

  private Socket socket;
  private ObjectOutputStream out;
  private ObjectInputStream in;

  private String username;
  private Message message;
  private Server server;
  private boolean connected;

  public ServerSocketHandler(String name, Socket socket, ObjectInputStream in,
      ObjectOutputStream out,Server server)
  {
    this.socket = socket;
    this.in = in;
    this.out = out;
    this.username = name;
    this.server=server;

    this.connected = true;
  }

  public void run()
  {
    while (connected)
    {
      try
      {
        message = (Message) in.readObject();
        System.out.println(message.getMessage());
        try
        {
          if (!message.isCommand())
          {
            for (ServerSocketHandler client : server.getPool().getConnections())
            {
              client.out.writeObject(message);
            }
          }
          else
          {
            if (message.getMessage().equals("exit"))
            {
              close();
            }
            if (message.getMessage().equals("Users"))
            {
              String str = "";
              for (ServerSocketHandler client : server.getPool().getConnections())
              {
                str += client.username + ", ";
              }
              System.out.println(str);

              for (ServerSocketHandler client : server.getPool().getConnections())
              {
                if (client.username.equals(username))
                {
                  client.sendMsg("There is  " + server.getPool().getConnections().size()
                      + " user connected \n" + str);
                }
              }
            }
          }
        }
        catch (NullPointerException e)
        {
        }
      }
      catch (IOException | ClassNotFoundException e)
      {
        System.out.println("No streams are incoming");
      }
    }
  }

  public void sendMsg(String msg)
  {
    try
    {
      out.writeObject(new Message("Server>>>", msg, false));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public String getUsername()
  {
    return username;
  }

  private void close()
  {
    try
    {
      this.connected = false;
      in.close();
      out.close();
      socket.close();
      server.getPool().removeConn(this);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}