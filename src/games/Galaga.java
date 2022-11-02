package games;

import java.util.HashMap;
import java.util.function.Supplier;

import gameComponent.ControlUnit.BreakOutPaddle;
import gameComponent.ControlUnit.GalagaShip;
import gameComponent.MovableObject.BallBreakout;
import gameComponent.MovableObject.BulletGalaga;
import gamePlaySystem.Player;
import gamePlaySystem.LevelSystem.BreakoutLevelControl;
import gamePlaySystem.LevelSystem.BreakoutLevel_1;
import gamePlaySystem.LevelSystem.BreakoutLevel_2;
import gamePlaySystem.LevelSystem.BreakoutLevel_3;
import gamePlaySystem.LevelSystem.GalagaLevelControl;
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


	// properties and variables associated with the Level
//	private final int TOTAL_LEVELS = 3;
//	private HashMap<Integer, Supplier<GameLevel>> levelToConstructorNoParameter;
	private int levelNum = 1;
	private int levelUpNum = 1;
	
	private GalagaLevelControl level;

	private Stage myStage;
	private Scene myScene;
	private Group root;
	private Player player;
	private BulletGalaga bullet;
	private GalagaShip ship;

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
		
		// create the winged layout in specific level
//		level.createNPCs(root);
//		level = new GalagaLevelControl(root, levelNum);
		level = new GalagaLevelControl(root, 3);

		// create player with the particular lives in each level
		player = new Player(level.getPlayerAllowedHealth());
		
		//create the ship
		ship = new GalagaShip(size);
		root.getChildren().add(ship.getShape());

		// create a place to see the shapes
		Scene scene = new Scene(root, size, size, background);
		return scene;
	}

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
		ship.handleKeyInput(null, player);
	}

	public void runGalaga() {
		launch();
	}

}
