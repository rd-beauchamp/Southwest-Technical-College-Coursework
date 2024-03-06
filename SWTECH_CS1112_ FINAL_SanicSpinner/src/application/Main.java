/*
 * TODO:
 * get Sanic in scene- Done!
 * Spin Sanic on axis- Done!
 * Set Spin time- Done!
 * set up spin counter(Upper right, horizontal alignment)-DONE (wants beautification)
 * set up play/pause (Lower central)- Done!
 * set up radial buttons for X/Y/Z axis (upper left, vertical placement)
 * OPTIONAL:
 * Set up speed slider
 * 
 */


package application;
	
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;


public class Main extends Application {
	private static int fastiness = 0;
	private String grandPoobah = fastiness + "";
	private double howCool = 500;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 800, 800);
			Image sanic = new Image(getClass().getResourceAsStream("Sanic.png"));
			ImageView sanicSpin = new ImageView(sanic);
			Text spinToWin = new Text("Spins: " + grandPoobah);
			Timeline counter = new Timeline(new KeyFrame(Duration.millis(howCool), 
					e-> {
						spinToWin.setText("Spins: " + getGrandPoobah(grandPoobah));
					}));
			RotateTransition gottaGoFast = new RotateTransition(Duration.millis(howCool), sanicSpin);
			
			Button bStop = new Button("||");
			Button bGo = new Button(">>");
			
			HBox bCarrier = new HBox();
			VBox rbHolder = new VBox();
			
			RadioButton zFlip = new RadioButton("Z Axis");
			RadioButton xFlip = new RadioButton("X Axis");
			RadioButton yFlip = new RadioButton("Y Axis");
			
			ToggleGroup flipDudes = new ToggleGroup();
			
			zFlip.setToggleGroup(flipDudes);
			xFlip.setToggleGroup(flipDudes);
			yFlip.setToggleGroup(flipDudes);
			
			Slider coolSlide = new Slider(.5, 3, 1.5);
			
			
			//sanic Animation Group
			gottaGoFast.setByAngle(360);
			gottaGoFast.setCycleCount(Timeline.INDEFINITE); 
			gottaGoFast.setInterpolator(Interpolator.LINEAR);
			gottaGoFast.play();
			
			//set counter
			counter.setCycleCount(Timeline.INDEFINITE);
			counter.play();
			
			//set sanic in scene
			sanicSpin.setFitHeight(400);
			sanicSpin.setFitWidth(400);
			root.setCenter(sanicSpin);
			
			//set text in scene
			root.setTop(spinToWin);
			BorderPane.setAlignment(spinToWin, Pos.TOP_RIGHT);
			
			//set pause button
			bStop.setOnMousePressed(e -> {
				gottaGoFast.stop();
				counter.stop();
			});
			
			//set go button
			bGo.setOnMousePressed(e -> {
				gottaGoFast.play();
				counter.play();
			});
			
			//set radio buttons
			zFlip.setOnAction(e -> {
				if(zFlip.isSelected()) {
				gottaGoFast.setAxis(Rotate.Z_AXIS);
				gottaGoFast.stop();
				counter.stop();
				gottaGoFast.play();
				counter.play();
				}
			});
			
			xFlip.setOnAction(e -> {
				if(xFlip.isSelected()) {
				gottaGoFast.setAxis(Rotate.X_AXIS);
				gottaGoFast.stop();
				counter.stop();
				gottaGoFast.play();
				counter.play();
				}
			});
			
			yFlip.setOnAction(e -> {
				if(yFlip.isSelected()) {
				gottaGoFast.setAxis(Rotate.Y_AXIS);
				gottaGoFast.stop();
				counter.stop();
				gottaGoFast.play();
				counter.play();
				}
			});
			
			//set up slider
			coolSlide.valueProperty().addListener(ov ->{	
				howCool = coolSlide.getValue();
				
				gottaGoFast.setRate(howCool);
				counter.setRate(howCool);
				
				
			
			});
			
			
			rbHolder.getChildren().addAll(zFlip, xFlip, yFlip);
			rbHolder.setAlignment(Pos.TOP_LEFT);
			root.setLeft(rbHolder);
			
			bCarrier.getChildren().addAll(bStop, bGo);
			bCarrier.setAlignment(Pos.TOP_CENTER);
			bCarrier.getChildren().add(coolSlide);
			
			root.setBottom(bCarrier);
			
			BorderPane.setAlignment(bCarrier, Pos.BOTTOM_CENTER);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String getGrandPoobah(String grandPoobah) {
		fastiness += 1;
		this.grandPoobah = fastiness + "";
		return this.grandPoobah;
	}

	public double getDreamcast(double howCool) {
		
		
		
		return this.howCool;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
