package gamePlaySystem;

import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import gamePlaySystem.LevelSystem.GameLevel;
import gamePlaySystem.PlayerMessaging;

/**
 * @author chris lee
 * handles Player information such as health, score, and whether the player is ready to play. 
 */

public class Player {
	private int health;
	private int score;
	private boolean isReadyToPlay;
	public static String MAX_SCORE_FILE = "resources/maxScore_";
	
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
		PlayerMessaging myPlayerMessaging = new PlayerMessaging(player);
		myPlayerMessaging.displayHealth(player);
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int scoreAmt) {
		score += scoreAmt;
	}
	
	public int getMaxScore(String gameName) {
		File maxData = new File(MAX_SCORE_FILE + gameName + ".txt");
		try{
			Scanner reader = new Scanner(maxData);
			if (reader.hasNextLine()) {
				int maxScore = reader.nextInt();
				reader.close();

				return maxScore;
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("No Such File Found");
			e.printStackTrace();
		}
		return 0;
	}
	
	public void updateMaxScore(String gameName) {
		if (getMaxScore(gameName) > score){
			File originalFile = new File(MAX_SCORE_FILE + gameName + ".txt");
			File tempFile = new File("tempfile.txt");
			try {
				PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
				pw.println(score);

				if (!new File(MAX_SCORE_FILE).delete()) {
					System.out.println("Could not delete file");
					return;
				}

				// Rename the new file to the filename the original file had.
				if (!tempFile.renameTo(originalFile)) {
					System.out.println("Could not rename file");
				}

			} catch (IOException e) {
				System.out.println("couln't write on the file.");
				e.printStackTrace();
			}

		}
	}
}
