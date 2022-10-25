package gameComponent.ControlUnit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class ControlUnit {
	
	@FXML
	protected ImageView image;
	public int canvasSize;
	public static final int PLATFORM_SPEED = 10;
	
	public ControlUnit(String Image, int canvasSize) {
		try{
			image = new ImageView(new Image(new FileInputStream(Image)));
		}
		catch (FileNotFoundException e) {
			System.out.println("Platform Image not found.");
		}
		this.canvasSize = canvasSize;
	}
	
	public void handleKeyInput (KeyCode code, Player player) {
		if (code == KeyCode.LEFT && image.getX() > 0) {
			image.setX(image.getX() - PLATFORM_SPEED);
			player.setReadytoPlay(true);
		}
		else if (code == KeyCode.RIGHT && image.getX()< canvasSize - RECTANGLE_WIDTH) {
			image.setX(image.getX() + PLATFORM_SPEED);
			player.setReadytoPlay(true);
		}
	}
}
