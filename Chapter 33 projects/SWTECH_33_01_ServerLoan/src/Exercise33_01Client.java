// Exercise31_01Client.java: The client sends the input to the server and receives
// result back from the server
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise33_01Client extends Application {
  // Text field for receiving radius
  private TextField tfAnnualInterestRate = new TextField();
  private TextField tfNumOfYears = new TextField();
  private TextField tfLoanAmount = new TextField();
  private Button btSubmit= new Button("Submit");

  // Text area to display contents
  private TextArea ta = new TextArea();
  private StringBuilder textEntry = new StringBuilder("");
  
  private double rate;
  private double years;
  private double amount;
  private double monthPay;
  private double totalPay;
	
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) throws IOException {
	Socket conn1 = new Socket("localhost", 12345);
//	Thread processedInput = new Thread(clientLogUpdate(conn1));
	DataOutputStream out = new DataOutputStream(conn1.getOutputStream());
	DataInputStream in = new DataInputStream(conn1.getInputStream());
    ta.setWrapText(true);
   
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Annual Interest Rate"), 0, 0);
    gridPane.add(new Label("Number Of Years"), 0, 1);
    gridPane.add(new Label("Loan Amount"), 0, 2);
    gridPane.add(tfAnnualInterestRate, 1, 0);
    gridPane.add(tfNumOfYears, 1, 1);
    gridPane.add(tfLoanAmount, 1, 2);
    gridPane.add(btSubmit, 2, 1);
    
    tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
    tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
    tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);
    
    tfLoanAmount.setPrefColumnCount(5);
    tfNumOfYears.setPrefColumnCount(5);
    tfLoanAmount.setPrefColumnCount(5);
            
    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(ta));
    pane.setTop(gridPane);
    
    
    btSubmit.setOnMouseClicked(e->{
    	System.out.println("ping");
    	rate = getDoubleFromField(tfAnnualInterestRate);
    	years = getDoubleFromField(tfNumOfYears);
    	amount = getDoubleFromField(tfLoanAmount);
    	try {
    	out.writeDouble(rate);
    	out.writeDouble(years);
    	out.writeDouble(amount);
    	
    	clientLogUpdate(in);
//    	clientLogUpdate();
    	} catch(IOException e1) {
  
    	}
    }); 
    
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    

  }
	

/**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) throws IOException {
    launch(args);
  }
  
  //method that rips value from text field
  public double getDoubleFromField(TextField textField) {
	  String text = textField.getText();
	  return Double.parseDouble(text);
	  
  }
  
  private String clientLogUpdate(DataInputStream in) throws IOException {
	  rate = in.readDouble();
	  years = in.readDouble();
	  amount = in.readDouble();
	  monthPay = in.readDouble();
	  totalPay = in.readDouble();
	  
	  String taEntry;
	  
	  taEntry = rate + "";
	  textEntry.append("Annual Interest Rate " + taEntry + " \n");
	  System.out.println("Ping!");
	  //input years
	  taEntry = years + "";
	  textEntry.append("Number of years for the Loan " + taEntry+ " \n");
	  System.out.println("Ping!");
	  //input amount
	  taEntry = amount + "";
	  textEntry.append("Loan Ammount $" + taEntry+ " \n");
	  System.out.println("Ping!");
	  //input monthly payment
	  taEntry = monthPay + "";
	  textEntry.append("monthly Payment $" + taEntry+ " \n");
	  System.out.println("Ping!");
	  //input total payment
	  taEntry = totalPay + "";
	  textEntry.append("Total Payment $" + taEntry+ " \n");
	  System.out.println("Ping! Final");
	  
	  ta.setText(textEntry.toString());
	  return textEntry.toString();
  }
 
}