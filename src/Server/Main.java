package Server;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{

  ServerView view = new ServerView();

  @Override public void start(Stage primaryStage)
  {
    view.init(primaryStage);
  }

  @Override public void stop()
  {
    try
    {
      view.stopServer();
    }
    catch (NullPointerException e)
    {
      System.out.println("Server closed");
    }
  }
}
