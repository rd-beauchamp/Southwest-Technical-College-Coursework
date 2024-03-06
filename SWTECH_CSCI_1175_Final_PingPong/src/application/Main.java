package application;
	
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ForkJoinTask;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	public TextArea debugConsole;
	private DataInputStream in;
	private DataOutputStream out;
	private Random rand = new Random();
	int x;
	int y;
	private Socket socket;
	private int z = 0;
	Thread tag;
	private ForkJoinTask weave;
	@Override
	public void start(Stage primaryStage) throws IOException {
		debugConsole = new TextArea();
		debugConsole.setPrefSize(400,400);
		VBox vbox = new VBox();
		vbox.setPrefSize(400, 400);
		vbox.getChildren().add(debugConsole);
		BorderPane root = new BorderPane();
		root.setCenter(vbox);

		Scene scene = new Scene(root,720,490);
		primaryStage.setTitle("DebugConsole");
		primaryStage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		new Thread (() -> {
			
			try {
				ServerSocket serverSocket = new ServerSocket(12345);
				socket = serverSocket.accept();
				debugConsole.appendText("Server Created\n");
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				while(true) {

					z++;	
					
					in = new DataInputStream(socket.getInputStream());
					debugConsole.appendText("loopCount " + z + " \n");
					
					
					out.writeInt(getBallCoord());
					out.writeInt(getBallCoord());
			            
			            boolean hit = in.readBoolean();
			            
			            if (hit) {
			                debugConsole.appendText("Player hit!\n");
			            } else {
			                debugConsole.appendText("Player missed!\n");
			            }
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}debugConsole.appendText("bucked from try");	
		}).start();
	}
	
	public int getBallCoord () {
		int rand1 =  rand.nextInt((8 - 1) + 1) + 1;
		return rand1;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
