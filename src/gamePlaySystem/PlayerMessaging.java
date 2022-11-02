package gamePlaySystem;

import java.awt.Color;
import java.util.logging.Level;

import gamePlaySystem.LevelSystem.BreakoutLevelControl;
import gamePlaySystem.LevelSystem.GalagaLevelControl;
import gamePlaySystem.LevelSystem.GameLevel;
import gamePlaySystem.LevelSystem.GameLevelControl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage; 
import javafx.scene.text.Text; 
import gamePlaySystem.LevelSystem.GameLevel;




public class PlayerMessaging {
	//variables
	private static final Text scoreMessage = new Text("Score: ");
	private static final Text healthMessage = new Text("Health Left: ");
	private static final Text levelMessage = new Text("Level: ");
	private static final Text endMessage = new Text("");
	private static final Text powerUpMessage = new Text("Power up!!!");
	private static final Text startingMessage = new Text("Press left or right arrow key to Start");
	
	
	public PlayerMessaging(Player player) {
		
	}

	// display current score
	public static Text displayScore(Player player) {
		scoreMessage.setX(40);
		scoreMessage.setY(20);
		scoreMessage.setText("Score: " + player.getScore());
		scoreMessage.setFill(javafx.scene.paint.Color.BLUEVIOLET);
		return scoreMessage;
	}

	// display health
	public static Text displayHealth(Player player) {
		healthMessage.setX(190);
		healthMessage.setY(20);		
		healthMessage.setText("Health: " + player.getHealth());
		healthMessage.setFill(javafx.scene.paint.Color.BLUEVIOLET);
		return healthMessage;
	}
	
	// display level number
	public static Text displayGalagaLevel(GalagaLevelControl level) {
		levelMessage.setText("Level: " + GameLevel.getCurrentLevel());
		getLevelmessage().setX(340);
		getLevelmessage().setY(20);
		levelMessage.setFill(javafx.scene.paint.Color.BLUEVIOLET);
		return getLevelmessage();
	}
	
	public static Text displayBreakoutLevel(BreakoutLevelControl level) {
		levelMessage.setText("Level: " + GameLevel.getCurrentLevel());
		getLevelmessage().setX(350);
		getLevelmessage().setY(20);
		levelMessage.setFill(javafx.scene.paint.Color.BLUEVIOLET);
		return getLevelmessage();
	}
	
	public static Text displayEndMessage(Player player, GalagaLevelControl level) throws InterruptedException {
		endMessage.setX(150);
		endMessage.setY(150);

		if (GameLevel.areAllLevelsPassed(level.getLevelNum())) {
			endMessage.setText("You win!");
			endMessage.setFill(javafx.scene.paint.Color.BLUEVIOLET);
			Thread.sleep(500);
			Platform.exit();
		}
		return scoreMessage;
	}
	public static Text displayDeathMessage(Player player){
		endMessage.setX(250);
		endMessage.setY(250);
		
		if (player.getHealth() == 0) {
			endMessage.setText("You lose! :(");
			endMessage.setFill(javafx.scene.paint.Color.BLUEVIOLET);
			//Platform.wait(500);
			Platform.exit();
		}
		return endMessage;
		
	}
	
	public static Text displayStartingMessage() {
		startingMessage.setX(150);
		startingMessage.setY(250);
		startingMessage.setFill(javafx.scene.paint.Color.BLUEVIOLET);
		return startingMessage;
	}

	public static Text getScoremessage() {
		return scoreMessage;
	}

	public static Text getLevelmessage() {
		return levelMessage;
	}




	
	// display score from lose health function, use that
	
	// display you win
//	public void displayWinningMessage() {
//		endMessage.setX(150);
//		endMessage.setY(25);
//	}
//	
//	public void displayLosingMessage() {
//		endMessage.setX(150);
//		endMessage.setY(25);
//	}
	
}
