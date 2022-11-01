package gamePlaySystem;

import java.util.logging.Level;

import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage; 
import javafx.scene.text.Text; 



public class PlayerMessaging {
	//variables
	private static final Text scoreMessage = new Text("Score: ");
	private static final Text healthMessage = new Text("Health Left: ");
	private static final Text levelMessage = new Text("Level: ");
	private static final Text endMessage = new Text("");
	private static final Text powerUpMessage = new Text("Power up!!!");
	
	
	public PlayerMessaging(Player player) {
		
	}

	// display current score
	public static Text displayScore(Player player) {
		scoreMessage.setX(50);
		scoreMessage.setY(25);
		scoreMessage.setText("Score: " + player.getScore());
		return scoreMessage;
	}

	// display health
	public static Text displayHealth(Player player) {
		healthMessage.setX(200);
		healthMessage.setY(25);
		healthMessage.setText("Health: " + player.getHealth());
		return healthMessage;
	}
	
	// display level number
	public static Text displayLevelNum(Level level) {
		getLevelmessage().setX(350);
		getLevelmessage().setY(25);
		return getLevelmessage();
	}

	public static Text getScoremessage() {
		return scoreMessage;
	}

	public static Text getLevelmessage() {
		return levelMessage;
	}
	
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
