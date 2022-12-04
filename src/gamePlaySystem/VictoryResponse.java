package gamePlaySystem;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Blake Byerly
 * 
 */

public class VictoryResponse {
	private Text endMessage = new Text();
	ImageView text = new ImageView();


// display you win
	public void displayWinningMessage() {
		endMessage.setText("You win!!!");
		endMessage.setX(150);
		endMessage.setY(25);
	}

	public void displayLosingMessage() {
		endMessage.setText("Game Over");
		endMessage.setX(150);
		endMessage.setY(25);
	}

	public Node getView() {
		// TODO Auto-generated method stub
		return text;
	}
}
