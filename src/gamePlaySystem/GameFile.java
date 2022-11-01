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
	private void getHighestScore() {
		
	}

}
