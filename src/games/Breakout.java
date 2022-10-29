package games;

import gameComponent.ControlUnit.BreakOutPaddle;
import gameComponent.MovableObject.BallBreakout;
import gamePlaySystem.Player;
import gamePlaySystem.LevelSystem.GameLevel;
import gamePlaySystem.LevelSystem.GameBreakoutLevel_1;
import gamePlaySystem.LevelSystem.GameBreakoutLevel_2;
import gamePlaySystem.LevelSystem.GameBreakoutLevel_3;

import java.util.HashMap;
import java.util.function.Supplier;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @Author: Hunter Copeland
 */

public class Breakout extends Game{

	
	// the image source of each elements
	public static final String PLATFORM_IMAGE = "resources/paddle.gif";
	public static final String WIN_IMAGE = "resources/youwinimage.jpeg";
	public static String BALL_IMAGE = "resources/ball.gif";

	// properties and variables associated with the Level
	private final int TOTAL_LEVELS = 3;
	private int levelNum = 1;
	private int levelUpNum = 1;
	private HashMap<Integer, Supplier<GameLevel>> levelToConstructorNoParameter;
	
	// properties and variables associated with the Level
	private GameLevel level;

	private Stage myStage;
	private BallBreakout ball;
  	private BreakOutPaddle platform;

	
	//private List<Brick> bricks;
	


	private Scene setupGame (int size, Paint background) {
		// create one top level collection to organize the things in the scene
		root = new Group();
		// create the bricks in specific level
		setUpLevelToConstructorNoParameterMap();
		Supplier<GameLevel> supplier = levelToConstructorNoParameter.get(levelNum);
		level = supplier.get();
		level.createBricks(root);
    
		// create player with the particular lives in each level
		player = new Player(level.getAllowedHealth());

		// create the ball
		ball = new BallBreakout(BALL_IMAGE, size, (int)(SIZE * level.STARTING_POSITION));
		root.getChildren().add(ball.getView());
    
		// create the platform
		this.platform = new BreakOutPaddle(SIZE);
		root.getChildren().add(platform.getShape());
    
		// create a place to see the shapes
		Scene scene = new Scene(root, size, size, background);
		return scene;
	}

	//Chris
	private void step (double elapsedTime) {
		if (player.isPlayerReady()) {
			moveFrame(elapsedTime);
		}
	}
	
	
	public void moveFrame(double elapsedTime) {
		// Chris: handles ball's behavior in the scene. (
		ball.handleBallMovement(elapsedTime, SIZE, platform, level, player); 

		
		// Brandon
		// deal with the collision between ball and bricks
		try {
			level.collideWithBricks(ball, root, player);
		}
		catch (Exception e) {}
		// Blake: level transition
		// Move to the next level if the player achieves the winning goal in the specific level; Or print the winning message and terminate the game when the player passes all levels
		if (level.getIsWinInEachLevel()) {
			levelTransition();
		}
	}
	
	// Blake
	private void levelTransition() {
		if (level.areAllLevelsPassed(levelNum)) {
			level.winningMessage();
			System.exit(0);
		}
		levelNum += levelUpNum;
		player.setReadytoPlay(false);
		myStage.close();
		start(new Stage());
	}
	
	// Brandon
	private void setUpLevelToConstructorNoParameterMap() {
		final int LEVEL_1 = 1;
		final int LEVEL_2 = 2;
		final int LEVEL_3 = 3;
		levelToConstructorNoParameter = new HashMap<Integer, Supplier<GameLevel>>();
		levelToConstructorNoParameter.put(LEVEL_1, GameBreakoutLevel_1::new);
		levelToConstructorNoParameter.put(LEVEL_2, GameBreakoutLevel_2::new);
		levelToConstructorNoParameter.put(LEVEL_3, GameBreakoutLevel_3::new);
	}
	
	public void runBreakout () {
		launch();
	}

	
	
}
