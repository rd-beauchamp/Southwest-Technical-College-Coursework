import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
  private TextArea taHistory = new TextArea();
  private TextArea taInput = new TextArea();
//  Socket conn1;
  String sender;
  private String append;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) throws IOException {
    taHistory.setWrapText(true);
    taHistory.setEditable(false);
    taInput.setWrapText(true);
   
    //taClient.setDisable(true);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene.fxml"));
	Controller controller = loader.getController();
   
	BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taHistory));
   
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taInput));

    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    
    new Thread (() -> {
    	try {
    		ServerSocket servSocket = new ServerSocket(12348);
    		Socket socket  = servSocket.accept();
    		
    	    DataInputStream fromClient = new DataInputStream(socket.getInputStream());
    	   	DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
    	   	
    	   	while (true) {
    	   		while (fromClient.available() > 0) {
    	   		append = fromClient.readUTF();  
    	   		taHistory.appendText("\n" + append);
    	   		}
	    	      taInput.setOnKeyPressed(e -> {
	    	  	   if(e.getCode() == KeyCode.ENTER) {
		    	    	  System.out.println("ServerPing enter hit!");
	    	 				try {
	    	 					String sender = textToSend();
	    	 					updateLocalOutputLog(taInput);
	    	 					toServer.writeUTF(sender);
	    	 					updateLocalHistory(sender);
	    	 				} catch (IOException e1) {
	    	 					// TODO Auto-generated catch block
	    	 					e1.printStackTrace();
	    	 				}
	    	  	   }
	    	      });

    	   	}
    	   	}catch(IOException ex) {
    	   		ex.printStackTrace();
    	   	}

    }).start();
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
  
  private void updateLocalOutputLog(TextArea taInput2) {
	taInput2.deleteText(0, taInput2.getLength());
}

  private void updateLocalHistory(String append) {
	this.append = append;
	taHistory.appendText("\n"  + append);
	
}
  
  private String getText(TextArea taInput) {
	  this.taInput = taInput;
	  String input = this.taInput.getText();
	  return input;
  }
  
  private String textToSend () {
	  String send = getText(taInput);
	  return send;
  }
}
