package gameComponent.NPCObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import games.Breakout;

/**
 * @author Xu Yan
 * 
 * GameNPC.java
 * 
 */

public abstract class GameNPC {
	
	// game display
	private final int CANVAS_WIDTH = Breakout.SIZE;
	private final int CANVAS_HEIGHT = Breakout.SIZE;
	
	// empty spaces between bricks 
	private final int BRICKS_SPACE_H = 6;
	private final int BRICKS_SPACE_V = 4;
	private int BRICKS_Y_OFFSET;
	
	// bricks display
	private int EACH_ROW_BRICK;
	private int BRICK_ROWS;
	
	// brick display
	private final int GENERAL_BRICK_HEIGHT = 8;
	private final int GENERAL_BRICK_SHRINK_SIZE = 0;
	private final int BRICK_IMAGE_HEIGHT = GENERAL_BRICK_HEIGHT;
	private int GENERAL_BRICK_WIDTH;
	private int BRICK_IMAGE_WIDTH;
	
	// brick image
	private ImageView brick;
	private String brickImageSrc;
	private int breakableBricksImageNumber;
	private int unbreakableBricksImageNumber;
	private String powerUpBricksImageType;
	
	// brick types and its properties
	private final String POWER_UP = "power-up";
	private final String BREAKABLE = "breakable";
	private final String UNBREAKABLE = "unbreakable";
	private HashMap<String, Integer> brickImageWidthOfEachBrickType;
	private HashMap<String, String> brickImageSourceOfEachBrickType;
	private HashMap<String, Boolean> brickBreakabilityOfEachBrickType;
	private String brickType;
	
	protected GameNPC(int brickRows, int eachRowBricks, int bricksOffsetFromTop, String brickTypePassed) {
		BRICK_ROWS = brickRows;
		EACH_ROW_BRICK = eachRowBricks;
		BRICKS_Y_OFFSET = bricksOffsetFromTop;
		GENERAL_BRICK_WIDTH = (CANVAS_WIDTH - GENERAL_BRICK_SHRINK_SIZE) / EACH_ROW_BRICK - BRICKS_SPACE_H;
		BRICK_IMAGE_WIDTH = GENERAL_BRICK_WIDTH;
		brickType = brickTypePassed;
	}
	
	// set the image, width & height of the image, and (x, y) coordinates of the brick
	public void setBrick(int col, int row) {	// protected void setBrick(int col, int row)
		// set some overall bricks properties
		setGenericBricksProperty();
		try {
			// set the brick's image
			brickImageSrc = brickImageSourceOfEachBrickType.get(brickType);
			Image brickImage = new Image(new FileInputStream(brickImageSrc));
			brick = new ImageView(brickImage);
		} catch(FileNotFoundException e) {
			System.out.println("Brick/Power-Up Image not found.");
		}
		// set the width and height of each brick image
		brick.setFitWidth(BRICK_IMAGE_WIDTH);
		brick.setFitHeight(BRICK_IMAGE_HEIGHT);
		// set the (x, y) coordinate of each brick
		brick.setX((BRICKS_SPACE_H / 2) + col * (GENERAL_BRICK_WIDTH + BRICKS_SPACE_H));
		brick.setY((BRICKS_Y_OFFSET) + row * (GENERAL_BRICK_HEIGHT + BRICKS_SPACE_V));
	}
	
	// set some overall bricks properties
	private void setGenericBricksProperty() {
		setBrickBreakabilityOfEachBrickType();
		setBrickImageWidthOfEachBrickType();
		setBrickImageSourceOfEachBrickType();
		BRICK_IMAGE_WIDTH = brickImageWidthOfEachBrickType.get(brickType);
	}

	// associate the brick type with its breakability
	private void setBrickBreakabilityOfEachBrickType() {
		brickBreakabilityOfEachBrickType = new HashMap<String, Boolean>();
		brickBreakabilityOfEachBrickType.put(POWER_UP, true);
		brickBreakabilityOfEachBrickType.put(BREAKABLE, true);
		brickBreakabilityOfEachBrickType.put(UNBREAKABLE, false);
	}
	
	// associate the brick type with its image display properties
	private void setBrickImageWidthOfEachBrickType() {
		int GENERAL_BRICK_IMAGE_WIDTH = GENERAL_BRICK_WIDTH;
		int POWERUP_BRICK_IMAGE_WIDTH = GENERAL_BRICK_WIDTH;
		brickImageWidthOfEachBrickType = new HashMap<String, Integer>();
		brickImageWidthOfEachBrickType.put(POWER_UP, POWERUP_BRICK_IMAGE_WIDTH);
		brickImageWidthOfEachBrickType.put(BREAKABLE, GENERAL_BRICK_IMAGE_WIDTH);
		brickImageWidthOfEachBrickType.put(UNBREAKABLE, GENERAL_BRICK_IMAGE_WIDTH);
	}
	
	// associate the brick type with its image source
	private void setBrickImageSourceOfEachBrickType() {
		String imgSrcPrefix = "resources/";
		String graphTypePng = ".png";
		String graphTypeGif = ".gif";
		String powerUpBrickImageSrc = imgSrcPrefix + powerUpBricksImageType + graphTypePng;
		String breakableBrickImageSrc = imgSrcPrefix + "brick" + breakableBricksImageNumber + graphTypeGif;
		String UnbreakableBrickImageSrc = imgSrcPrefix + "brick" + unbreakableBricksImageNumber + graphTypeGif;
		brickImageSourceOfEachBrickType = new HashMap<String, String>();
		brickImageSourceOfEachBrickType.put(POWER_UP, powerUpBrickImageSrc);
		brickImageSourceOfEachBrickType.put(BREAKABLE, breakableBrickImageSrc);
		brickImageSourceOfEachBrickType.put(UNBREAKABLE, UnbreakableBrickImageSrc);
	}
	
	protected void setPowerUpBricksImageType(String powerUpType) {
		powerUpBricksImageType = powerUpType;
	}
	
	protected void setBreakableBricksImageNumber(int breakableImgNum) {
		breakableBricksImageNumber = breakableImgNum;
	}
	
	protected void setUnbreakableBricksImageNumber(int unbreakableImgNum) {
		unbreakableBricksImageNumber = unbreakableImgNum;
	}
	
	public Node getBrick() {
		return brick;
	}
	
	public ImageView getBrickImageView() {
		return brick;
	}
	
	protected boolean getBreakability(String brickTypePassed) {
		return brickBreakabilityOfEachBrickType.get(brickTypePassed);
	}
	
}
