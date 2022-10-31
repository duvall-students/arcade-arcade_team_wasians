package games;

import java.util.HashMap;
import java.util.function.Supplier;

import gameComponent.ControlUnit.BreakOutPaddle;
import gameComponent.MovableObject.BallBreakout;
import gamePlaySystem.Player;
import gamePlaySystem.LevelSystem.BreakoutLevel_1;
import gamePlaySystem.LevelSystem.BreakoutLevel_2;
import gamePlaySystem.LevelSystem.BreakoutLevel_3;
import gamePlaySystem.LevelSystem.GalagaLevel_1;
import gamePlaySystem.LevelSystem.GalagaLevel_2;
import gamePlaySystem.LevelSystem.GalagaLevel_3;
import gamePlaySystem.LevelSystem.GameLevel;
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

public class Galaga extends Application implements Game{

	// properties of the canvas
	public static final Paint BACKGROUND = Color.AZURE;
	public static final int SIZE = 400;

	// properties of the keyboard key movement
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;


	// properties and variables associated with the Level
	private final int TOTAL_LEVELS = 3;
	private int levelNum = 1;
	private int levelUpNum = 1;
	private HashMap<Integer, Supplier<GameLevel>> levelToConstructorNoParameter;

	// properties and variables associated with the Level
	private GameLevel level;
	private Scene myScene;
	protected Stage myStage;
	protected Group root;
	protected Player player;

	// private List<Brick> bricks;

	@Override
	public void start(Stage stage) {

		myScene = setupGame(SIZE, BACKGROUND);
		myStage = new Stage();
		myStage = stage;
		myStage.setScene(myScene);
		myStage.show();

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
		
		/* TODO:
		 * Leaving this in for now, will be switched with the level spawner for Galaga levels
		 */
		// create the bricks in specific level
		setUpLevelToConstructorNoParameterMap();
		Supplier<GameLevel> supplier = levelToConstructorNoParameter.get(levelNum);
		level = supplier.get();
		level.createBricks(root);

		// create player with the particular lives in each level
		player = new Player(level.getAllowedHealth());


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

		/* TODO:
		 * 		- move the ship
		 * 		- shoot from the ship
		 * 		- collision with the bad guys
		 */
		
		
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
		levelToConstructorNoParameter.put(LEVEL_1, GalagaLevel_1::new);
		levelToConstructorNoParameter.put(LEVEL_2, GalagaLevel_2::new);
		levelToConstructorNoParameter.put(LEVEL_3, GalagaLevel_3::new);
	}

	public void runGalaga() {
		launch();
	}

}
