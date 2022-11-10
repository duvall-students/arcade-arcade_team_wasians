package gamePlaySystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Xu Yan
 * 
 * GameFile.java
 * Implement the game-file system responsible for recording the game score according to different Arcade Games.
 * (some functionalities of the game-file system are also in the "Player.java" from the "gamePlaySystem" package)
 * 
 */

public class GameFile {
	
	private String gameName;
	private String fileName;
	private String filePath;
	
	public GameFile(String presentGameName) {
		gameName = presentGameName;
		fileName = gameName + "_scores.txt";
		filePath = fileName;
		makeNewFile();
	}
	
	// create a new score record file by the game category if the file doesn't exist yet
	private void makeNewFile() {
		new File(filePath);
	}
	
	// write the new score to the score record file
	public void writeToFile(int score) {
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(String.valueOf(score));
			writer.close();
		} catch (IOException e) {
			System.out.println("Something is going wrong.");
		}
	}

	// read the old score in the score record file
	public void readScoreFile() {
		try {
			File file = new File(filePath);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String score = scanner.nextLine();
				System.out.println(score);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something is going wrong.");
		}
	}

	// read the old score in the score record file and get the highest score
	public void getHighestScore() {
		int maxScore = 0;
		
		try {
			File file = new File(filePath);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String scoreStr = scanner.nextLine();
				int scoreInt = Integer.parseInt(scoreStr); 
				if (scoreInt > maxScore) {
					maxScore = scoreInt;
				}
			}
			System.out.println(maxScore);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something is going wrong.");
		}
	}

}
