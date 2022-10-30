package gamePlaySystem.LevelSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import gameComponent.NPCObject.GameNPC;

import java.util.Random;
import java.util.Arrays;

import javafx.scene.Group;

/**
 * @author Blake B. Byerly and Xu Yan
 * 
 * GameLevel.java
 * 
 */

public abstract class GameLevel {
	
	/**
	 * variables / properties about the level in the Breakout Game.
	 */
	// variables associated with bricks in each level
	protected List<GameNPC> breakableBricks;
    protected List<GameNPC> unbreakableBricks;
    protected List<GameNPC> powerUpBricks;
    private HashMap<Integer, String> powerUpBrickSettingsInLevels;
    protected HashMap<String, List<GameNPC>> bricksListOfEachBrickType;
    protected GameNPC npc;
    private int currentLevel;
	// properties of the bricks
	private final String BREAKABLE = "breakable";
	protected final String UNBREAKABLE = "unbreakable";
	protected HashMap<Integer, String> integerToStringOfEachBrickType;
    protected int bricksOffsetFromTop;
    protected boolean isBreakable;
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
    
	/**
	 * variables / properties about the level in the Galaga Game
	 */
	protected final String GREEN = "green";
	protected final String RED = "red";
	protected final String YELLOW = "yellow";
	private List<GameNPC> greenWinged;
    protected List<GameNPC> redWinged;
    protected List<GameNPC> yellowWinged;
    protected List<GameNPC> powerUpWinged;
    private HashMap<Integer, String> powerUpWingedSettingsInLevels;
    protected HashMap<String, List<GameNPC>> wingedsListOfEachWingedType;
    protected HashMap<Integer, String> integerToStringOfEachWingedType;
    private HashMap<String, Supplier<GameNPC>> wingedToConstructorNoParameter;
    
	/**
	 * variables / properties about the level in every Arcade Game
	 */
    protected List<GameNPC> allNPCs;
    protected int[][] gameNPCLayout;
	protected int eachRowNPCs;
	protected final String EMPTY = "empty";
	protected final String POWER_UP = "power-up";
	protected final int FIRST_ROW = 0;
    
    protected GameLevel(int bricksYOffsetInLevel, int levelNum) {
    	// initialize objects
    	allNPCs = new ArrayList<>();
        
    	// assign variables
    	bricksOffsetFromTop = bricksYOffsetInLevel;
    	currentLevel = levelNum;
    	levelScore = INITIAL_SCORE;
    }
   
	// associate the level with its particular power-up in Breakout Game
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
	
	// associate the level with its particular power-up in Galaga Game
	private void setPowerUpWingedSettingsInLevels() {
		// variables about the level
		final int LEVEL_1 = 1;
		final int LEVEL_2 = 2;
		final int LEVEL_3 = 3;
		powerUpWingedSettingsInLevels = new HashMap<Integer, String>();
		powerUpWingedSettingsInLevels.put(LEVEL_1, "scorepower");
		powerUpWingedSettingsInLevels.put(LEVEL_2, "scorepower");
		powerUpWingedSettingsInLevels.put(LEVEL_3, "scorepower");
	}
	
	// associate the brick type with its List of GameNPC in Breakout Game
	private void setBricksListOfEachBrickType() {
		bricksListOfEachBrickType = new HashMap<String, List<GameNPC>>();
		bricksListOfEachBrickType.put(POWER_UP, powerUpBricks);
		bricksListOfEachBrickType.put(BREAKABLE, breakableBricks);
		bricksListOfEachBrickType.put(UNBREAKABLE, unbreakableBricks);
	}
	
	// associate the winged type with its List of GameNPC in Galaga Game
	private void setBricksListOfEachWingedType() {
		wingedsListOfEachWingedType = new HashMap<String, List<GameNPC>>();
		wingedsListOfEachWingedType.put(RED, redWinged);
		wingedsListOfEachWingedType.put(GREEN, greenWinged);
		wingedsListOfEachWingedType.put(YELLOW, yellowWinged);
		wingedsListOfEachWingedType.put(POWER_UP, powerUpWinged);
	}
	
	// associate the integer label with string label of each brick type in Breakout Game
	private void setIntegerToStringOfEachBrickType() {
		integerToStringOfEachBrickType = new HashMap<Integer, String>();
		integerToStringOfEachBrickType.put(0, EMPTY);
		integerToStringOfEachBrickType.put(1, POWER_UP);
		integerToStringOfEachBrickType.put(2, BREAKABLE);
		integerToStringOfEachBrickType.put(3, UNBREAKABLE);
	}
	
	// associate the integer label with string label of each winged type in Galaga Game
	private void setIntegerToStringOfEachWingedType() {
		integerToStringOfEachWingedType = new HashMap<Integer, String>();
		integerToStringOfEachWingedType.put(0, EMPTY);
		integerToStringOfEachWingedType.put(1, POWER_UP);
		integerToStringOfEachWingedType.put(2, GREEN);
		integerToStringOfEachWingedType.put(3, RED);
		integerToStringOfEachWingedType.put(4, YELLOW);
	}
	
	protected void winCheckForLevel() {
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
