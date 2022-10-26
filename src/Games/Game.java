package Games;

import java.util.HashMap;
import java.util.function.Supplier;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * @Author: Hunter Copeland
 */

public class Game extends Application {
	
	//properties of the canvas
	public static final Paint BACKGROUND = Color.AZURE;
	public static final int SIZE = 400;

	// properties of the keyboard key movement
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	private Scene myScene;
	private Stage myStage;
	private Player player;
	private Group root;
	
	@Override
	public void start(Stage stage){

		myScene = setupGame(SIZE, BACKGROUND);
		myStage = new Stage();
		myStage = stage;
		myStage.setScene(myScene);
		myStage.show();
		myScene.setOnKeyPressed(e -> platform.handleKeyInput(e.getCode(), player));

		
		// attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	public Scene setupGame(int size, Paint background) {
		return null;
	}

}
