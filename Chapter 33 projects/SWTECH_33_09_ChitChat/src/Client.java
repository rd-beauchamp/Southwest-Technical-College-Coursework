import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import static javafx.application.Application.launch;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application {
  private TextArea taHistory = new TextArea();
  private TextArea taInput = new TextArea();
  private String sender;
  private String append;
 
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) throws IOException {
	    taHistory.setWrapText(true);
	    taHistory.setEditable(false);
	    taInput.setWrapText(true);
	   
	    //taClient.setDisable(true);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene.fxml"));
	Controller controller = loader.getController();
   
//    DataInputStream fromClient = null;
//   	DataOutputStream toServer = null;
   	
	BorderPane pane3 = new BorderPane();
    pane3.setTop(new Label("History"));
    pane3.setCenter(new ScrollPane(taHistory));
   
    BorderPane pane4 = new BorderPane();
    pane4.setTop(new Label("New Message"));
    pane4.setCenter(new ScrollPane(taInput));

    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane3, pane4);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    new Thread (() -> {
    	try {
    		Socket socket = new Socket("localhost", 12348);
    		
    		DataInputStream fromClient = new DataInputStream(socket.getInputStream());
    		DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());	   	
    	   	
    		while (true) {
    	   		while (fromClient.available() > 0) {
        	   		append = fromClient.readUTF();
        	   		taHistory.appendText("\n" + append);
        	   		}
    
	    	      taInput.setOnKeyPressed(e -> {
	    	  	   if(e.getCode() == KeyCode.ENTER) {
		    	    	System.out.println("Client Ping enter hit");
	    	 				try {
	    	 					String sender = textToSend();
	    	 					updateLocalOutputLog(taInput);
	    	 					updateLocalHistory(sender);
	    	 					toServer.writeUTF(sender);
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
	taHistory.appendText("\n" + append);
	
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