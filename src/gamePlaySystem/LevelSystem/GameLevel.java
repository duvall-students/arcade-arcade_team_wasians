package gamePlaySystem.LevelSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javafx.scene.Group;

import games.Breakout;
import gameComponent.MovableObject.BallBreakout;
import gameComponent.NPCObject.GameNPC;
import gameComponent.NPCObject.GameNPCBreakoutBrickBreakable;
import gameComponent.NPCObject.GameNPCBreakoutBrickPowerUp;
import gameComponent.NPCObject.GameNPCBreakoutBrickUnbreakable;
import gamePlaySystem.Player;

/**
 * @author Blake B. Byerly and Xu Yan
 * 
 * GameLevel.java
 * 
 */

public abstract class GameLevel {
	
	// variables associated with bricks in each level
	private List<GameNPC> allBricks;
	private List<GameNPC> breakableBricks;
    private List<GameNPC> unbreakableBricks;
    private List<GameNPC> powerUpBricks;
    private List<Integer> unbreakableBricksNums;
    private List<Integer> powerUpBricksNums;
    private List<Integer> emptyBricksNums;
    private HashMap<Integer, String> powerUpBrickSettingsInLevels;
    private HashMap<String, Integer> bricksQtyOfEachBrickType;
    private HashMap<String, List<Integer>> bricksNumsOfEachBrickType;
    private HashMap<String, List<GameNPC>> bricksListOfEachBrickType;
    private GameNPC brick;
    private int currentLevel;
    
	// properties of the bricks
    private final String EMPTY = "empty";
    private final String POWER_UP = "power-up";
	private final String BREAKABLE = "breakable";
	private final String UNBREAKABLE = "unbreakable";
	protected final int EMPTY_INT = 0;
	protected final int POWER_UP_INT = 1;
	protected final int BREAKABLE_INT = 2;
	protected final int UNBREAKABLE1_INT = 3;
	private HashMap<Integer, String> integerToStringOfEachBrickType;
	protected int[][] bricksLayout;
    private int bricksOffsetFromTop;
    private int eachRowBricks;
    private int brickRows;
    private int emptyBricksQty;
    private int powerUpBricksQty;
    private int unbreakableBricksQty;
    private int breakableBricksQty;
    private int totalBricksQty;
    private boolean isBreakable;
    
    // properties of the brick images
    private int breakableBricksImageNum;
    private int unbreakableBricksImageNum;
    private final int BRICK_IMAGE_SIZE = 10;
    private final ArrayList<Integer> BREAKABLE_BRICK_IMAGE_NUMBER = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 10));
	private final ArrayList<Integer> UNBREAKABLE_BRICK_IMAGE_NUMBER = new ArrayList<>(Arrays.asList(6, 7, 8, 9));
    
    // properties of the brick points
    private final int INITIAL_SCORE = 0;
    private final int EACH_BRICK_POINT = 1;
    private int levelScore;
    private int totalScore;
    
    // variables that declare victory
    private boolean isWinnerInLevel;
    private boolean hasPrinted;
    
    // variables associated with the player
    protected int allowedHealth;
    
    // properties of the ball position
    public double STARTING_POSITION = 3.0/4.0;
    
    // properties of the level
    private final int TOTAL_LEVELS = 3;

    /* 
     * 2.
     * the growth of the unbreakable bricks quantity in each level is 2n+3 (n starts with 1, add 1 when each level up);
     * the growth of the empty space that existed in each level is 5n (n starts with 0; add 1 when each level up);
     * the growth of the power-up bricks that existed in each level is n (n starts with 1; add 1 when each level up)
     */
    protected GameLevel(int brickRowsInLevel, int eachRowBricksInLevel, int unbreakableBricksQtyInLevel, int powerUpBricksQtyInLevel, int emptyBricksQtyInLevel, int bricksYOffsetInLevel, int levelNum) {
    	// initialize objects
    	allBricks = new ArrayList<>();
    	powerUpBricks = new ArrayList<>();
    	breakableBricks = new ArrayList<>();
    	unbreakableBricks = new ArrayList<>();
    	unbreakableBricksNums = new ArrayList<>();
    	powerUpBricksNums = new ArrayList<>();
    	emptyBricksNums = new ArrayList<>();
        
    	// assign variables
    	brickRows = brickRowsInLevel;
    	eachRowBricks = eachRowBricksInLevel;
    	emptyBricksQty = emptyBricksQtyInLevel;
    	powerUpBricksQty = powerUpBricksQtyInLevel;
    	unbreakableBricksQty = unbreakableBricksQtyInLevel;
    	bricksOffsetFromTop = bricksYOffsetInLevel;
    	currentLevel = levelNum;
    	totalBricksQty = eachRowBricks * brickRows;
    	breakableBricksQty = totalBricksQty - (unbreakableBricksQty + powerUpBricksQty);
    	levelScore = INITIAL_SCORE;
    	
    	// set some generic properties
    	setPowerUpBrickSettingsInLevels();
    	setBricksQtyOfEachBrickType();
    	setBricksNumsOfEachBrickType();
    	setBricksListOfEachBrickType();
    	setIntegerToStringOfEachBrickType();
    }
   
    // create the brick based on its type
    protected void createBricks(Group root) {
    	setBreakableBricksImageNum();
    	setUnbreakableBricksImageNum();
    	for (int row = 0; row < bricksLayout.length; row++) {
    		for (int col = 0; col < bricksLayout[row].length; col++) {
    			String typeStr = integerToStringOfEachBrickType.get(bricksLayout[row][col]);
    			if (!typeStr.equals(EMPTY)) {
    				generateBricks(col, row, root, typeStr);
    			}
    		}
    	}
    }
	
	// load the brick on the screen
	private void generateBricks(int col, int row, Group root, String brickType) {
		if (brickType.equals(POWER_UP)) {
			brick = new GameNPCBreakoutBrickPowerUp(brickRows, eachRowBricks, bricksOffsetFromTop, powerUpBrickSettingsInLevels.get(currentLevel));
		} else if (brickType.equals(UNBREAKABLE)) {
			brick = new GameNPCBreakoutBrickUnbreakable(brickRows, eachRowBricks, bricksOffsetFromTop, unbreakableBricksImageNum);
		} else {
			brick = new GameNPCBreakoutBrickBreakable(brickRows, eachRowBricks, bricksOffsetFromTop, breakableBricksImageNum);
		}
		brick.setBrick(col, row);
		bricksListOfEachBrickType.get(brickType).add(brick);
		allBricks.add(brick);
		root.getChildren().add(brick.getBrick());
	}
	
	// deal with the collision of the ball and bricks
	protected void collideWithBricks(BallBreakout ball, Group root, Player player) {
		for (GameNPC b: allBricks) {
			if (ball.getView().getBoundsInParent().intersects(b.getBrick().getBoundsInParent())) {
				if (unbreakableBricks.contains(b)) {
					isBreakable = false;
				}
				else {
					isBreakable = true;
				}
				if (isBreakable) {
					breakableBricks.remove(b);
					allBricks.remove(b);
					root.getChildren().remove(b.getBrick());
					player.addScore(1);
				}
				if (powerUpBricks.contains(b)) {
					ball.powerUpBall();
				}
				ball.bounceOnRectangle(b.getBrickImageView(), false);
			}
			winCheckForLevel();
		}
	}
	
	// determine which brick is the designated type of brick
	protected void setBricksNumByBrickType(String brickType) {
		int num;
		int qty = 0;
		int bricksQty = bricksQtyOfEachBrickType.get(brickType);
		List<Integer> bricksNums = bricksNumsOfEachBrickType.get(brickType);
		while(qty != bricksQty) {
			num = pickRandomNumber(totalBricksQty);
			if(!bricksNums.contains(num)) {
				bricksNums.add(num);
				qty++;
			}
		}
	} 
	
	// determine which image to using for breakable brick
	private void setBreakableBricksImageNum() {
		breakableBricksImageNum = pickRandomNumber(BRICK_IMAGE_SIZE);
		while(!BREAKABLE_BRICK_IMAGE_NUMBER.contains(breakableBricksImageNum)) {
			breakableBricksImageNum = pickRandomNumber(BRICK_IMAGE_SIZE);
		}
	}
	
	// determine which image to using for unbreakable brick
	private void setUnbreakableBricksImageNum() {
		unbreakableBricksImageNum = pickRandomNumber(BRICK_IMAGE_SIZE);
		while(!UNBREAKABLE_BRICK_IMAGE_NUMBER.contains(unbreakableBricksImageNum)) {
			unbreakableBricksImageNum = pickRandomNumber(BRICK_IMAGE_SIZE);
		}
	}
	
	// associate the brick type with its brick positions (sequence)
	private void setBricksNumsOfEachBrickType() {
		bricksNumsOfEachBrickType = new HashMap<String, List<Integer>>();
		bricksNumsOfEachBrickType.put(EMPTY, emptyBricksNums);
		bricksNumsOfEachBrickType.put(POWER_UP, powerUpBricksNums);
		bricksNumsOfEachBrickType.put(UNBREAKABLE, unbreakableBricksNums);
	}
	
	// associate the level with its particular power-up
	private void setPowerUpBrickSettingsInLevels() {
		// variables about the level
		final int LEVEL_1 = 1;
		final int LEVEL_2 = 2;
		final int LEVEL_3 = 3;
		powerUpBrickSettingsInLevels = new HashMap<Integer, String>();
		powerUpBrickSettingsInLevels.put(LEVEL_1, "speedballpower");
		powerUpBrickSettingsInLevels.put(LEVEL_2, "speedballpower");
		powerUpBrickSettingsInLevels.put(LEVEL_3, "speedballpower");
	}
	
	// associate the brick type with its quantities
	private void setBricksQtyOfEachBrickType() {
		bricksQtyOfEachBrickType = new HashMap<String, Integer>();
		bricksQtyOfEachBrickType.put(EMPTY, emptyBricksQty);
		bricksQtyOfEachBrickType.put(POWER_UP, powerUpBricksQty);
		bricksQtyOfEachBrickType.put(UNBREAKABLE, unbreakableBricksQty);
	}
	
	// associate the brick type with its List of Brick
	private void setBricksListOfEachBrickType() {
		bricksListOfEachBrickType = new HashMap<String, List<GameNPC>>();
		bricksListOfEachBrickType.put(POWER_UP, powerUpBricks);
		bricksListOfEachBrickType.put(BREAKABLE, breakableBricks);
		bricksListOfEachBrickType.put(UNBREAKABLE, unbreakableBricks);
	}
	
	// associate the integer label with string label of each brick type
	private void setIntegerToStringOfEachBrickType() {
		integerToStringOfEachBrickType = new HashMap<Integer, String>();
		integerToStringOfEachBrickType.put(0, EMPTY);
		integerToStringOfEachBrickType.put(1, POWER_UP);
		integerToStringOfEachBrickType.put(2, BREAKABLE);
		integerToStringOfEachBrickType.put(3, UNBREAKABLE);
	}
	
	private void winCheckForLevel() {
		if (isLevelAccomplished()) {
			isWinnerInLevel = true;
			winningMessage();
		}
	}
	
	// displays winning message to the screen
    public void winningMessage() {
    	if (isWinnerInLevel && !hasPrinted) {
    		System.out.println("You win!");
    		hasPrinted = true;
    	}
    }
	
	private int pickRandomNumber(int range) {
		Random numRandom = new Random();
		return numRandom.nextInt(range) + 1;
	}
	
	public int getAllowedHealth() {
		return allowedHealth;
	}
	
	private boolean isLevelAccomplished() {
		final int ALL_CLEAR = 0;
		return breakableBricks.size() == ALL_CLEAR;
	}
	
	public boolean getIsWinInEachLevel() {
		return isWinnerInLevel;
	}
	
	public boolean areAllLevelsPassed(int currentLevel) {
		return currentLevel == TOTAL_LEVELS;
	}
	
}
