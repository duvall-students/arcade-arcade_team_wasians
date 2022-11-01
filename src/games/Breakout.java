package games;

import gameComponent.ControlUnit.BreakOutPaddle;
import gameComponent.MovableObject.BallBreakout;
import gamePlaySystem.Player;
import gamePlaySystem.LevelSystem.GameLevel;
import gamePlaySystem.LevelSystem.GameLevelControl;
import gamePlaySystem.LevelSystem.BreakoutLevelControl;
import gamePlaySystem.LevelSystem.BreakoutLevel_1;
import gamePlaySystem.LevelSystem.BreakoutLevel_2;
import gamePlaySystem.LevelSystem.BreakoutLevel_3;
import gamePlaySystem.LevelSystem.GalagaLevel_1;
import gamePlaySystem.LevelSystem.GalagaLevel_2;
import gamePlaySystem.LevelSystem.GalagaLevel_3;

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

public class Breakout extends Application implements Game{


	// properties and variables associated with the Level
	private int levelNum = 1;
	private int levelUpNum = 1;

	// properties and variables associated with the Level
	private GameLevel level;
	private BallBreakout ball;
	private BreakOutPaddle platform;
	private Scene myScene;
	protected Stage myStage;
	protected Group root;
	protected Player player;
	
	private HashMap<Integer, Supplier<GameLevel>> levelToConstructorNoParameter;

	// private List<Brick> bricks;

	@Override
	public void start(Stage stage) {

		myScene = setupGame(SIZE, BACKGROUND);
		myStage = new Stage();
		myStage = stage;
		myStage.setScene(myScene);
		myStage.show();
		myScene.setOnKeyPressed(e -> platform.handleKeyInput(e.getCode(), player));

		// attach "game loop" to timeline to play it (basically just calling step()
		// method repeatedly forever)
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	public Scene setupGame(int size, Paint background) {
		// create one top level collection to organize the things in the scene
		root = new Group();
		// create the bricks in specific level
		level.createNPCs(root);

		// create player with the particular lives in each level
		player = new Player(level.getAllowedHealth());

		// create the ball
		ball = new BallBreakout(size, (int) (SIZE * level.getBallStartingPosition()));
		root.getChildren().add(ball.getView());

		// create the platform
		this.platform = new BreakOutPaddle(SIZE);
		root.getChildren().add(platform.getShape());

		// create a place to see the shapes
		Scene scene = new Scene(root, size, size, background);
		return scene;
	}

	// Chris
	public void step(double elapsedTime) {
		if (player.isPlayerReady()) {
			moveFrame(elapsedTime);
		}
	}

	public void moveFrame(double elapsedTime) {
		// Chris: handles ball's behavior in the scene. (
		ball.handleBallMovement(elapsedTime, SIZE, platform, level, player);
		// Brandon
		// deal with the collision between ball and bricks
		level.getElementsCollisionInEachLevel(myStage, root, ball, player);
		// Blake: level transition
		// Move to the next level if the player achieves the winning goal in the
		// specific level; Or print the winning message and terminate the game when the
		// player passes all levels
		if (level.getIsWinInEachLevel()) {
			levelNum += levelUpNum;
			start(new Stage());
		}
	}

	public void runBreakout() {
		launch();
	}

	@Override
	public void levelTransition() {
		if (level.areAllLevelsPassed(levelNum)) {
			level.winningMessage();
			System.exit(0);
		}
		levelNum += levelUpNum;
		player.setReadytoPlay(false);
		myStage.close();
		start(new Stage());
		
	}

	public void setUpLevelToConstructorNoParameterMap() {
		final int LEVEL_1 = 1;
		final int LEVEL_2 = 2;
		final int LEVEL_3 = 3;
		levelToConstructorNoParameter = new HashMap<Integer, Supplier<GameLevel>>();
		levelToConstructorNoParameter.put(LEVEL_1, BreakoutLevel_1::new);
		levelToConstructorNoParameter.put(LEVEL_2, BreakoutLevel_2::new);
		levelToConstructorNoParameter.put(LEVEL_3, BreakoutLevel_3::new);
		
	}

}
