package games;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Supplier;

import gameComponent.ControlUnit.BreakOutPaddle;
import gameComponent.ControlUnit.GalagaShip;
import gameComponent.MovableObject.BallBreakout;
import gameComponent.MovableObject.BulletGalaga;
import gameComponent.NPCObject.NPCGalaga;
import gamePlaySystem.Player;
import gamePlaySystem.PlayerMessaging;
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
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @Author: Hunter Copeland
 */

public class Galaga extends Application implements Game {
	
	public static final Paint BACKGROUND = Color.BLACK;
	
	// properties and variables associated with the Level
	private int levelNum = 1;
	private int levelUpNum = 1;

	// properties and variables associated with the Galaga
	private GalagaLevelControl level;
	private Stage myStage;
	private Scene myScene;
	private Group root;
	private Player player;
	private PlayerMessaging playerMessaging;
	private BulletGalaga bullet;
	private GalagaShip ship;
	private NPCGalaga npc;
	public Collection<BulletGalaga> bulletList;

	@Override
	public void start(Stage stage) {

		myScene = setupGame(SIZE, BACKGROUND);
		myStage = new Stage();
		myStage = stage;
		myStage.setScene(myScene);
		myStage.show();
		
		bulletList = new ArrayList<>();
		
		myScene.setOnKeyPressed(e -> spawner(e.getCode()));

		// attach "game loop" to timeline to play it (basically just calling step()
		// method repeatedly forever)
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void spawner(KeyCode code) {
		
		System.out.println("Spawner run");
		
		if(code == KeyCode.SPACE) {
			handleKeyInputBullet(code);
		} else {
			ship.handleKeyInput(code, player);
		}
	}

	public Scene setupGame(int size, Paint background) {
		// create one top level collection to organize the things in the scene
		root = new Group();

		// create the winged layout in specific level
		level = new GalagaLevelControl(root, levelNum);

		// set up the player with particular lives
		player = new Player(level.getPlayerAllowedHealth());

		// create the ship
		ship = new GalagaShip(size);
		root.getChildren().add(ship.getShape());
		
		// add text
		playerMessaging = new PlayerMessaging(player);
		root.getChildren().add(playerMessaging.displayGalagaLevel(level));
		root.getChildren().add(playerMessaging.displayScore(player));
		root.getChildren().add(playerMessaging.displayStartingMessage());
		
		// create a place to see the shapes
		Scene scene = new Scene(root, size, size, background);
		return scene;
	}

	public void step(double elapsedTime) {
		if (player.isPlayerReady()) {
			moveFrame(elapsedTime);
			playerMessaging.displayStartingMessage().setText("");
		}
		else if (player.getHealth() == 0) {
			playerMessaging.displayStartingMessage().setText("");
		}
		else {
			playerMessaging.displayStartingMessage().setText("Press left or right arrow key to Start");
		}
	}

	public void moveFrame(double elapsedTime) {		
		
		level.getWingedMove(elapsedTime, ship);
		
		//myScene.setOnKeyPressed(e -> handleKeyInputBullet(e.getCode()));
		try {
			for(BulletGalaga bullet : bulletList) {
				bullet.move(elapsedTime);
				level.getElementsCollisionInEachLevel(myStage, root, bullet, player, levelNum, bulletList, ship, playerMessaging);
			}
		} catch(Exception e) {
			System.out.println("bullet collided");
		}
		
		if (level.checkIsWinInEachLevel()) {
			levelNum += levelUpNum;
			start(new Stage());
		}
	}
	
	private  void handleKeyInputBullet(KeyCode code) {
		if (code == KeyCode.SPACE) {
			bullet = new BulletGalaga(SIZE, (int) ship.getX(), ship);
			bulletList.add(bullet);
			root.getChildren().add(bullet.getView());
		}
	}

	public void runGalaga() {
		launch();
	}

}
