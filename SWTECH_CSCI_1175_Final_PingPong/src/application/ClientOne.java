package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientOne extends Application{
	private DataInputStream in;
	private DataOutputStream out;
	
	private Random rand = new Random();
	
	private int randal1;
	private int randal2;
	
	private Button ball;
	private Button hint1;
	private Button hint2;
	private Button button;
	
	private VBox gridHolderOne;
	
	private String buttonID;
	private String buttonName;
	
	private TextArea feedback;
	
	private GridPane playerGrid;
	private boolean hit;
	@Override
	public void start(Stage primaryStage) throws IOException  {		
			BorderPane root = new BorderPane();
			playerGrid = new GridPane();
			feedback = new TextArea();
			feedback.setEditable(false);
			//create boxes to hold grid
			gridHolderOne = new VBox();
			VBox textHolder = new VBox();
			textHolder.setPrefSize(200, 200);
			
			gridHolderOne.setPrefSize(480, 720);
			//create grids
			buildGrid(playerGrid);
			
			//place player 1 grid
			textHolder.getChildren().addAll(feedback);
			gridHolderOne.getChildren().addAll(playerGrid);;
			root.setLeft(gridHolderOne);
			root.setRight(textHolder);
			
			Scene scene = new Scene(root,720,490);
//			scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e ->{
//				System.out.println("global event fired");
//				if (e.getTarget() instanceof Button) {
//					Button eventButton = (Button) e.getTarget();
//					deleteButton(playerGrid, "Hint!");
//					deleteButton(playerGrid, "Ball!");
//				}
//			});
			primaryStage.setTitle("Player_One");
			primaryStage.setResizable(false);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			new Thread (() -> {
				try {
					Socket socket = new Socket("localhost", 12345);
					out = new DataOutputStream(socket.getOutputStream());
					in = new DataInputStream(socket.getInputStream());
					
						while(true) {
							while(in.available()>0) {
								feedback.appendText("Ball to you!");
								randal1 = in.readInt();
								randal2 = in.readInt();
								
								Platform.runLater(() -> {
								try {
									serveBall(playerGrid, randal1, randal2);
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								
								});
								
						}
					}

				} catch(Exception e) {
				e.printStackTrace();
				}
				System.out.println("Bucked from try");
			}).start();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void buildGrid (GridPane pane) throws IOException{
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
			Button button = new Button(j + "" + i);
			button.setMinSize(50, 50);
			button.setOnMousePressed(e->{
				feedback.appendText("\n Miss!");
				hit = false;
				try {
					out.writeBoolean(hit);
					deleteButton(playerGrid, "Hint!");
					deleteButton(playerGrid, "Ball!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			pane.add(button, i, j);
			}
		}
	}

	//get hint
	private void getHint(GridPane pane, int rand1, int rand2) throws IOException{
		hint1 = new Button("Hint!");
		hint1.setMinSize(50, 50);
		hint1.setOnMousePressed(e->{
			feedback.appendText("\n Why would you click this? MISS!");
			hit = false;
			try {
				out.writeBoolean(hit);
				deleteButton(playerGrid, "Hint!");
				deleteButton(playerGrid, "Ball!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		hint2 = new Button("Hint!");
		hint2.setMinSize(50, 50);
		hint2.setOnMousePressed(e->{
			feedback.appendText("\n Why would you click this? MISS!");
			hit = false;
			try {
				out.writeBoolean(hit);
				deleteButton(playerGrid, "Hint!");
				deleteButton(playerGrid, "Ball!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		int picker1 =  rand.nextInt(8);
		int picker2 = rand.nextInt(8);
		//debugging
		System.out.println("DebugMe " + picker1 + "  " + picker2);
		
		//check for duplicate case choices
			if (dupeChecker(picker1, picker2) == true && picker1 != 8){
				picker1 = picker1 + 1;
				System.out.println("DebugMe in dupe check " + picker1 + "  " + picker2);
			}else if (dupeChecker(picker1, picker2) == true && picker1 != 0){
				picker1 = picker1 + 1;
				System.out.println("DebugMe in else dupe check" + picker1 + "  " + picker2);
			}
			
			getHintButton(pane, rand1, rand2, picker1, hint1);
			getHintButton(pane, rand1, rand2, picker2, hint2);
			
		}

	private void getHintButton(GridPane pane, int rand1, int rand2, int picker, Button hint) {
		hint.setMinSize(50, 50);
		int newCol = 0;
		int newRow = 0;
			switch (picker){
			case 8 ://top left of button
				System.out.println("case 8");
				newCol = rand1 - 1;
				newRow = rand2 - 1;
				break;
			case 7 ://top of button
				System.out.println("case 7");
				newCol = rand1;
				newRow = rand2 - 1;
				break;
			case 6 ://top right of button
				System.out.println("case 6");
				newCol = rand1 + 1;
				newRow = rand2 - 1;
				break;
			case 5 ://middle left of button
				System.out.println("case 5");
				newCol = rand1 - 1;
				newRow = rand2;
				break;
			case 4 ://middle right of button
				System.out.println("case 4");
				newCol = rand1 + 1;
				newRow = rand2;
				break;
			case 3 ://lower left of button
				System.out.println("case 3");
				newCol = rand1 - 1;
				newRow = rand2 + 1;
				break;
			case 2 ://lower middle of button
				System.out.println("case 2");
				newCol = rand1;
				newRow = rand2 + 1;
				break;
			default://lower right of button
				System.out.println("case default");
				newCol = rand1 + 1;
				newRow = rand2 + 1;
				break;
				
			}
			pane.add(hint, newCol, newRow);

	}
	
	//duplicate checker
	boolean dupeChecker(int picker1, int picker2) {
		if (picker1 != picker2) {
			return false;
		}
		return true;
	}
	//This Method will add the ball
	private void serveBall(GridPane pane, int rand1, int rand2) throws IOException{
		ball = new Button("Ball!");
		ball.setOpacity(0);
		ball.setMinSize(50, 50);
		ball.setOnMousePressed(e -> {
			feedback.appendText("\n Hit!");
			hit = true;
			try {
				out.writeBoolean(hit);
				deleteButton(playerGrid, "Hint!");
				deleteButton(playerGrid, "Ball!");
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		pane.add(ball, rand1, rand2);
		getHint(pane, rand1, rand2);
	}
	
    private void deleteButton(GridPane pane, String buttonText) {
        pane.getChildren().removeIf(node -> {
            if (node instanceof Button) {
                Button button = (Button) node;
                return button.getText().equals(buttonText);
            }
            return false;
        });
    }
	
}

