// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class Main extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();
  private StringBuilder textEntry = new StringBuilder("");
  
  //loan information
  protected Loan loan = new Loan();
  protected double monthlyPay;
  protected double totalPay;
  Socket conn1 = new Socket();

  private double rate;
  private double years;
  private double amount;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) throws IOException {
	  launchServer();
	  DataInputStream in = new DataInputStream(conn1.getInputStream());

	  rate = in.readDouble();
	  years = in.readDouble();
	  amount = in.readDouble();
	  System.out.println(rate + " " + years + " " + amount + " debugMe");
	  
	  process(rate, years, amount);
	  getMonthly();
	  getTotal();
	  
	  //convert the information to a string in a separate set of variables
	  //i.e. print in server log
	  
	//method here to handle client input
    ta.setWrapText(true);
    

    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
//    while(true) {
    Platform.runLater(() -> { 
    	toServerLog();
    	try {
			serverReturn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    });
//    }
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
  	
  public void launchServer() throws IOException {
	  ServerSocket mainConn = new ServerSocket(12345);
	  conn1 = mainConn.accept();
  }
  
  

  private Loan process(double rate, double years, double amount) {
	  //set loan parameters
	  loan.setAnnualInterestRate(rate);
	  loan.setNumberOfYears(years);
	  loan.setLoanAmount(amount);
	  loan.getLoanDate();

	  
	  return loan;
  }
  
  private double getMonthly() {
	  return monthlyPay = loan.getMonthlyPayment();
  }
  
  private double getTotal() {
	  return totalPay = loan.getTotalPayment();
  }
  
  private String toServerLog() {
	  String taEntry;
	  textEntry.append(loan.getLoanDate().toString() +  "\n");
	  //input rate
	  taEntry = rate + "";
	  textEntry.append("Annual Interest Rate " + taEntry + " \n");
	  //input years
	  taEntry = years + "";
	  textEntry.append("Number of years for the Loan " + taEntry+ " \n");
	  //input amount
	  taEntry = amount + "";
	  textEntry.append("Loan Ammount $" + taEntry+ " \n");
	  //input monthly payment
	  taEntry = loan.getMonthlyPayment() + "";
	  textEntry.append("monthly Payment $" + taEntry+ " \n");
	  //input total payment
	  taEntry = loan.getTotalPayment() + "";
	  textEntry.append("Total Payment $" + taEntry+ " \n");
	  
	  ta.setText(textEntry.toString());
	  return textEntry.toString();

  }
  
  public DataOutputStream serverReturn () throws IOException {
	  DataOutputStream out = new DataOutputStream(conn1.getOutputStream());
	  out.writeDouble(loan.getAnnualInterestRate());
	  out.writeDouble(loan.getNumberOfYears());
	  out.writeDouble(loan.getLoanAmount());
	  out.writeDouble(loan.getMonthlyPayment());
	  out.writeDouble(loan.getTotalPayment());
	  return out;
	  
  }
  
}

