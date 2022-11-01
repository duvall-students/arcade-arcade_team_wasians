package games;

import gamePlaySystem.Player;

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

/**
 * @Author: Hunter Copeland
 */

interface Game {

	// properties of the canvas
	public static final Paint BACKGROUND = Color.AZURE;
	public static final int SIZE = 400;

	// properties of the keyboard key movement
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	

	// the image source of each elements for breakout
	public static final String PLATFORM_IMAGE = "resources/paddle.gif";
	public static final String WIN_IMAGE = "resources/youwinimage.jpeg";
	public static String BALL_IMAGE = "resources/ball.gif";

	public void start(Stage stage);

	public Scene setupGame(int size, Paint background);

	public void step(double elapsedTime);

	public void moveFrame(double elapsedTime);

	public void levelTransition();

	public void setUpLevelToConstructorNoParameterMap();

}
