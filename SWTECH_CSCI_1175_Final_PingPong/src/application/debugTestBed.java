package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class debugTestBed extends Application{
	private DataInputStream in;
	private DataOutputStream out;
	private Random rand = new Random();

	@Override
	public void start(Stage primaryStage) throws IOException  {		

		try {
			BorderPane root = new BorderPane();
			GridPane playerGrid = new GridPane();
			//create boxes to hold grid
			VBox gridHolderOne = new VBox();
			gridHolderOne.setPrefSize(480, 720);
			//create grids
			buildGrid(playerGrid);

			serveBall(playerGrid);
			//place player 1 grid
			gridHolderOne.getChildren().addAll(playerGrid);
			root.setLeft(gridHolderOne);

			
			
			Scene scene = new Scene(root,720,490);
			primaryStage.setTitle("debugMe");
			primaryStage.setResizable(false);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void buildGrid (GridPane pane) {
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
			Button button = new Button(j + "" + i);
			button.setMinSize(50, 50);
			pane.add(button, i, j);
			}
		}
	}

	//get hint
	private void getHint(GridPane pane, int rand1, int rand2) {
		Button hint1 = new Button("Hint!");
		hint1.setMinSize(50, 50);
		Button hint2 = new Button("Hint!");
		hint1.setMinSize(50, 50);
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
	private boolean dupeChecker(int picker1, int picker2) {
		if (picker1 != picker2) {
			return false;
		}
		return true;
	}
	//This Method will add the ball
	private void serveBall(GridPane pane) {
		int rand1 = rand.nextInt((8 - 1) + 1) + 1;
		int rand2 = rand.nextInt((8 - 1) + 1) + 1;
		System.out.println("DebugMe randoms " + rand1 + " " + rand2);
		Button ball = new Button("Ball!");
		ball.setOpacity(0);
		ball.setMinSize(50, 50);
		pane.add(ball, rand1, rand2);
		getHint(pane, rand1, rand2);
	}
	

}
