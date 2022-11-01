package gamePlaySystem.LevelSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import gameComponent.MovableObject.BallBreakout;
import gameComponent.NPCObject.GameNPC;
import gamePlaySystem.Player;

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
    
	// variables / properties about the level in every Arcade Game
	protected GameNPC npc;
    protected List<GameNPC> allNPCs;
    protected int[][] gameNPCLayout;
	protected int eachRowNPCs;
	protected final String EMPTY = "empty";
	protected final String POWER_UP = "power-up";
	protected final int FIRST_ROW = 0;
	protected int npcsOffsetFromTop;
	private int currentLevel;
	// properties of the level points
    private final int INITIAL_SCORE = 0;
    private int currentLevelScore;
    private int totalScore;
    // properties of the level
    protected int TOTAL_LEVELS;
    // variables associated with the player
    protected int allowedHealth;
    // variables that declare victory
 	private boolean isWinnerInLevel;
 	private boolean hasPrinted;
    
    protected GameLevel(int npcsYOffsetInLevel, int levelNum) {
    	// initialize objects
    	allNPCs = new ArrayList<>();
    	// assign variables
    	npcsOffsetFromTop = npcsYOffsetInLevel;
    	currentLevel = levelNum;
    	currentLevelScore = INITIAL_SCORE;
    }
    
    // create the NPC based on its type
    protected abstract void createNPCs(Group root);
    
    // load the NPC on the screen
//    protected abstract void generateNPCs(int col, int row, Group root, String npcType);
    
    // deal with the collision of the MovableObject and NPCObject
    protected abstract void collideWithNPCs(Group root, BallBreakout ball, Player player);
	
	public int getAllowedHealth() {
		return allowedHealth;
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
	
	private boolean isLevelAccomplished() {
		final int ALL_CLEAR = 0;
//		return breakableBricks.size() == ALL_CLEAR;
		return true;
	}
	
	public boolean getIsWinInEachLevel() {
		return isWinnerInLevel;
	}
	
	public boolean areAllLevelsPassed(int currentLevel) {
		return currentLevel == TOTAL_LEVELS;
	}
	
}
