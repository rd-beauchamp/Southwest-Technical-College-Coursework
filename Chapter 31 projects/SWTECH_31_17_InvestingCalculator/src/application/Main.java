package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	//value for missing input
	private double nullVal = 0;
	private double amount;
	private double years;
	private double rate;
	private double matureVal;
	
	@Override
	public void start(Stage stage) {
		try{
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());

			//create text field objects		
			TextField tfAmount = new TextField();
			TextField tfYears = new TextField();
			TextField tfRate = new TextField();
			TextField tfMature = new TextField();
			tfMature.setEditable(false);
			//create labels for text fields
			Label lAmount = new Label("Investment Amount: ");
			Label lYears = new Label("Total Duration in Years: ");
			Label lRate = new Label("Annual Interest Rate: ");
			Label lMature = new Label("At Maturity: ");
			
			//create button to calculate
			Button btCalculate = new Button("Calculate");
			btCalculate.setPrefSize(100, 35);
			
			//create menu
			MenuBar menuBar = new MenuBar();
			Menu menuOperation = new Menu("Operation");
			
			
			//add functions to menu
			MenuItem menuCalculate = new MenuItem("Calculate");
			MenuItem menuExit = new MenuItem("Exit");
			
			menuOperation.getItems().addAll(menuCalculate, menuExit);
			
			//add menu to menubar
			menuBar.getMenus().addAll(menuOperation);
			//make box to hold menu
			HBox menuBox = new HBox(10);
			menuBox.getChildren().addAll(menuBar);
			
			//make box for buttons
			HBox buttonBox = new HBox(20);
			buttonBox.getChildren().addAll(btCalculate);
			
			//box for text fields
			VBox fieldBox = new VBox(2);
			fieldBox.getChildren().addAll(tfAmount, tfYears, tfRate, tfMature);
			
			//box for labels
			VBox labelBox = new VBox(10);
			labelBox.getChildren().addAll(lAmount, lYears, lRate, lMature);
			menuBar.prefWidthProperty().bind(stage.widthProperty());			
			root.setTop(menuBox);
			root.setBottom(buttonBox);
			root.setCenter(fieldBox);
			root.setLeft(labelBox);
			
			//set listeners and make things do things
			//button!
			btCalculate.setOnMouseClicked( e ->{
			System.out.println("calculate clicked!");
			amount = getVal(tfAmount);
			years = getVal(tfYears);
			rate = getVal(tfRate);
			
			tfMature.clear();
			Investment investment = new Investment(amount, years, rate);
			matureVal = investment.calculate();
			tfMature.appendText("$" + matureVal + "");
			
			});
			
			//menu items!
			menuCalculate.setOnAction(e -> {
				System.out.println(" Menu calculate clicked!");
				amount = getVal(tfAmount);
				years = getVal(tfYears);
				rate = getVal(tfRate);
				
				tfMature.clear();
				Investment investment = new Investment(amount, years, rate);
				matureVal = investment.calculate();
				tfMature.appendText("$" + matureVal + "");
			});
			
			menuExit.setOnAction(e -> {
				System.out.println("Exit Clicked! Terminating!");
				Platform.exit();
			});
			
			stage.setTitle("SWTECH_31_17_InterestCalculator.java");
			stage.setResizable(true);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	private double getVal(TextField tf) {
		// fetch text return double, return 0 if non double
		String val = tf.getText();
		double retVal;
		
		if (isDouble(val)) {
		return retVal = Double.parseDouble(val);
		}else {
			System.out.println("ERROR! VALUE NOT A DOUBLE");
			return nullVal;
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}
