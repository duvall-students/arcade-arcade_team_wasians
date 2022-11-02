package gamePlaySystem;

import gamePlaySystem.LevelSystem.GameLevel;
import javafx.scene.text.Text;
import gamePlaySystem.PlayerMessaging;

/**
 * @author chris lee
 * handles Player information such as health, score, and whether the player is ready to play. 
 */


public class Player {
	private int health;
	private int score;
	private boolean isReadyToPlay;
	
	public Player(int health) {
		this.health = health;
		this.score = 0;
		isReadyToPlay = false;
	}
	
	public void setReadytoPlay(boolean status) {
		isReadyToPlay = status;
	}
	
	public boolean isPlayerReady() {
		return isReadyToPlay;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void rechargeHealth(GameLevel level) {
		health = level.getAllowedHealth();
	}
	
	public boolean isPlayerDead() {
		return health<=0;
	}
	
	public void playerLoseHealth(Player player) {
		health--;
		PlayerMessaging.displayHealth(player);
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int scoreAmt) {
		score += scoreAmt;
	}
}
