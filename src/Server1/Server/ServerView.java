package Server1.Server;

import Server1.MODEL.DataModelManagerS;
import Server1.MODEL.DataModelS;
import Shared.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class ServerView
{
  @FXML Label label;
  private Server server;

  private Thread serverThread;

  /** Method to run when server gui is opened */
  public void init(Stage stage)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../FXML/server.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Server");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /** Method to run when user click on "Start server" JavaFX button */
  @FXML public void startServer()
  {
    label.setText("Server is running...");
    this.server = new Server(2910);

    serverThread = new Thread(server);
    serverThread.start();
  }

  /** Method to run when user click on "Stop server" JavaFX button */
  @FXML public void stopServer()
  {
    label.setText("Server is turned off");
    server.setRunning(false);
    server.closeServerSocket();
  }

}
