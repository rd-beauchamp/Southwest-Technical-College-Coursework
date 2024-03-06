package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
  private Map<String, Integer>[] mapForBoy = new HashMap[10];
  private Map<String, Integer>[] mapForGirl = new HashMap[10];
  
  private Button btFindRanking = new Button("Find Ranking");
  private ComboBox<Integer> cboYear = new ComboBox<>();
  private ComboBox<String> cboGender = new ComboBox<>();
  private TextField tfName = new TextField();
  private Label lblResult = new Label();
 
  private List<String> sourceURL = new ArrayList<>();
  private String[] boys;
  private String[] girls;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) throws IOException {
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Select a year:"), 0, 0);
    gridPane.add(new Label("Boy or girl?"), 0, 1);
    gridPane.add(new Label("Enter a name:"), 0, 2);
    gridPane.add(cboYear, 1, 0);
    gridPane.add(cboGender, 1, 1);
    gridPane.add(tfName, 1, 2);
    gridPane.add(btFindRanking, 1, 3);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setHgap(5);
    gridPane.setVgap(5);
  
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(gridPane);
    borderPane.setBottom(lblResult);
    BorderPane.setAlignment(lblResult, Pos.CENTER);
    
    // source URL's based in arrayList for future proofing.
    sourceURL.add("http://liveexample.pearsoncmg.com/data/babynamesranking2001.txt");
    sourceURL.add("http://liveexample.pearsoncmg.com/data/babynamesranking2002.txt");
    sourceURL.add("http://liveexample.pearsoncmg.com/data/babynamesranking2003.txt");
    sourceURL.add("http://liveexample.pearsoncmg.com/data/babynamesranking2004.txt");
    sourceURL.add("http://liveexample.pearsoncmg.com/data/babynamesranking2005.txt");
    sourceURL.add("http://liveexample.pearsoncmg.com/data/babynamesranking2006.txt");
    sourceURL.add("http://liveexample.pearsoncmg.com/data/babynamesranking2007.txt");
    sourceURL.add("http://liveexample.pearsoncmg.com/data/babynamesranking2008.txt");
    sourceURL.add("http://liveexample.pearsoncmg.com/data/babynamesranking2009.txt");
    sourceURL.add("http://liveexample.pearsoncmg.com/data/babynamesranking2010.txt");
    
    getURL(sourceURL);

    btFindRanking.setOnMouseClicked( e -> {
    	if (cboGender.getValue().equals("Male")) {
    		lblResult.setText("" + mapForBoy[cboYear.getValue() -2001].get(tfName.getText()));
  
    	}else if (cboGender.getValue().equals("Female")) {
    		lblResult.setText("" + mapForGirl[cboYear.getValue() -2001].get(tfName.getText()));
    	}
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 370, 160);
    primaryStage.setTitle("Baby Name Popularity"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    for (int year = 2001; year <= 2010; year++) {
      cboYear.getItems().add(year);
    }
    cboYear.setValue(2001);    
    cboGender.getItems().addAll("Male", "Female");
    cboGender.setValue("Male");
    
  }
  
  
  public void getURL(List sourceURL) throws IOException{
	  for(int i = 0; i < sourceURL.size(); i++) {
		String pie = sourceURL.get(i).toString();
		URL url = new URL(pie);
		Scanner input = new Scanner(url.openStream());
		
		mapForBoy[i] = new HashMap();
		mapForGirl[i] = new HashMap();
		
		while(input.hasNext()) {
			Integer rank = input.nextInt();
			String boyName = input.next();
			input.nextInt();
			String girlName = input.next();
			input.nextInt();
			mapForBoy[i].put(boyName, rank);
			mapForGirl[i].put(girlName, rank);
		}		 
	  }	    
  }
  
  public static void main(String[] args) {
    launch(args);
  }
  
}
