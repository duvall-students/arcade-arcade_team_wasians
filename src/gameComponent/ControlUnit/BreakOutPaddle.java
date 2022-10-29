package gameComponent.ControlUnit;
/**
 * @author chris lee
 * handles Platform behaviors such as, moving left and right on the screen
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//import javafx.beans.binding.BooleanBinding;
//import javafx.beans.property.BooleanProperty;
//import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

public class BreakOutPaddle extends ControlUnit{

	//private BooleanProperty leftPressed = new SimpleBooleanProperty();
	//private BooleanProperty rightPressed = new SimpleBooleanProperty();
	//private BooleanBinding keyPressed = (leftPressed).or(rightPressed);
	
	
	public static final int RECTANGLE_WIDTH = 100;
	public static final int OBJECT_SPEED = 10;

	public static final double PLATFORM_HALF_WIDTH = RECTANGLE_WIDTH / 2.0;
	public static final double PLATFORM_X_LOC = 1.0/2;
	public static final double PLATFORM_Y_LOC = 8.0/9;
	public static final Paint PLATFORM_COLOR = Color.NAVY;
	
	public static final String PLATFORM_IMAGE = "resources/paddle.gif";
	public int canvasSize;


	public BreakOutPaddle(int canvasSize) {
		super(PLATFORM_IMAGE, canvasSize);
		
		image.setX((canvasSize * PLATFORM_X_LOC) - PLATFORM_HALF_WIDTH);
		image.setY(PLATFORM_Y_LOC * canvasSize); 
		image.setFitWidth(RECTANGLE_WIDTH);
	}

	

	

}
