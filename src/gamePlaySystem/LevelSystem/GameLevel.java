package gamePlaySystem.LevelSystem;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.Group;

import gameComponent.NPCObject.GameNPC;

/**
 * @author Blake B. Byerly and Xu Yan
 * 
 * GameLevel.java
 * Implement the creation of NPC, the motion of NPC, collision between movable player objects and NPC objects,
 * level and game victory check and response, and other game and level properties in the specific Arcade Game.
 * 
 */

public abstract class GameLevel {
    
	// variables / properties about the level in every Arcade Game
	protected GameNPC npc;
    protected List<GameNPC> allNPCs;
    protected int[][] gameNPCLayout;
	protected int eachRowNPCs;
	protected final String EMPTY = "empty";
	protected final String POWER_UP = "power-up";
	protected final int FIRST_ROW = 0;
	protected int npcsOffsetFromTop;
	private static int currentLevel;
	// properties of the level points
//    private final int INITIAL_SCORE = 0;
//    private int currentLevelScore;
//    private int totalScore;
    // properties of the level
    protected int TOTAL_LEVELS;
    // variables associated with the player
    protected int allowedHealth;
    // variables that declare victory
 	protected boolean isWinnerInLevel;
 	private boolean hasPrinted;
    
    protected GameLevel(int npcsYOffsetInLevel, int levelNum) {
    	// initialize lists
    	allNPCs = new ArrayList<>();
    	// assign variables
    	npcsOffsetFromTop = npcsYOffsetInLevel;
    	currentLevel = levelNum;
//    	currentLevelScore = INITIAL_SCORE;
    }
    
    // create the NPC based on its type
    public abstract void createNPCs(Group root);
   
	// check if the player wins in the specific level
	protected abstract void winCheckForLevel();
	
	// displays winning message to the screen when the player passes all levels in each game
    public void winningMessage() {
    	if (isWinnerInLevel && !hasPrinted) {
    		System.out.println("You win!");
    		hasPrinted = true;
    	}
    }
	
    // get if the player wins in the specific level
	public boolean getIsWinningAtEachLevel() {
		return isWinnerInLevel;
	}
	
	// check if the player passes all levels in the specific game
	public boolean areAllLevelsPassed(int currentLevel) {
		return currentLevel == TOTAL_LEVELS;
	}
	
	// get the player's allowed initial lives
	public int getAllowedHealth() {
		return allowedHealth;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
}
