package gamePlaySystem.LevelSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.Group;

import gameComponent.MovableObject.BallBreakout;
import gameComponent.NPCObject.GameNPC;
import gamePlaySystem.Player;
import gamePlaySystem.PlayerMessaging;

/**
 * @author Xu Yan
 * 
 * BreakoutLevels.java
 * Implement the creation of NPC, the motion of NPC, collision between movable player objects and NPC objects,
 * and other game and level properties in the Breakout.
 * 
 */

public abstract class BreakoutLevels extends GameLevel {

	// variables associated with bricks in each level
	protected List<GameNPC> breakableBricks;
	protected List<GameNPC> unbreakableBricks;
	protected List<GameNPC> powerUpBricks;
	private HashMap<Integer, String> powerUpBrickSettingsInLevels;
	protected HashMap<String, List<GameNPC>> bricksListOfEachBrickType;
	// properties of the bricks
	private final String BREAKABLE = "breakable";
	protected final String UNBREAKABLE = "unbreakable";
	protected HashMap<Integer, String> integerToStringOfEachBrickType;
	protected boolean isBreakable;
	// bricks scores
	private final int EACH_BRICK_POINT = 1;
	// properties of the ball position
	public double STARTING_POSITION = 3.0/4.0;
	
	protected BreakoutLevels(int bricksYOffsetInLevel, int levelNum) {
		super(bricksYOffsetInLevel, levelNum);
		TOTAL_LEVELS = 3;
		// initialize the setup of collections
		initializeBricksListOfEachBrickType();
		setBricksListOfEachBrickType();
		setPowerUpBrickSettingsInLevels();
		setIntegerToStringOfEachBrickType();
	}
	
	// create the brick based on its type
	@Override
	public void createNPCs(Group root) {
		for (int row = 0; row < gameNPCLayout.length; row++) {
    		for (int col = 0; col < gameNPCLayout[row].length; col++) {
    			String typeStr = integerToStringOfEachBrickType.get(gameNPCLayout[row][col]);
    			if (!typeStr.equals(EMPTY)) {
    				generateBricks(col, row, root, typeStr);
    			}
    		}
    	}
	}
	
	// load the brick on the screen
	protected abstract void generateBricks(int col, int row, Group root, String brickType);
	
	// deal with the collision of the ball and bricks
	protected void collideWithNPCs(Group root, BallBreakout ball, Player player, PlayerMessaging playerMessaging) {
		final boolean BREAKABLE = true;
		final boolean UNBREAKABLE = false;
		final boolean isNotPaddle = false;
		for (GameNPC npc: allNPCs) {
			if (ball.getView().getBoundsInParent().intersects(npc.getNPC().getBoundsInParent())) {
				isBreakable = BREAKABLE;
				if (unbreakableBricks.contains(npc)) {
					isBreakable = UNBREAKABLE;
				}
				if (isBreakable) {
					breakableBricks.remove(npc);
					allNPCs.remove(npc);
					root.getChildren().remove(npc.getNPC());
					player.addScore(1);
					playerMessaging.displayScore(player);
				}
				if (powerUpBricks.contains(npc)) {
					ball.powerUpBall();
				}
				ball.bounceOnRectangle(npc.getNPCImageView(), isNotPaddle);
			}
			playerMessaging.displayDeathMessage(player);
			winCheckForLevel();
		}
	}
	
	// get the ball starting position
	public double getBallStartingPositionNow() {
		return STARTING_POSITION;
	}
	
	// initialize each brick type list
	private void initializeBricksListOfEachBrickType() {
		powerUpBricks = new ArrayList<>();
		breakableBricks = new ArrayList<>();
		unbreakableBricks = new ArrayList<>();
	}
	
	// associate the level with its particular power-up type in Breakout Game
	private void setPowerUpBrickSettingsInLevels() {
		final int LEVEL_1 = 1;
		final int LEVEL_2 = 2;
		final int LEVEL_3 = 3;
		powerUpBrickSettingsInLevels = new HashMap<Integer, String>();
		powerUpBrickSettingsInLevels.put(LEVEL_1, "speedballpower");
		powerUpBrickSettingsInLevels.put(LEVEL_2, "speedballpower");
		powerUpBrickSettingsInLevels.put(LEVEL_3, "speedballpower");
	}
	
	// associate the brick type with its List of GameNPC in Breakout Game
	private void setBricksListOfEachBrickType() {
		bricksListOfEachBrickType = new HashMap<String, List<GameNPC>>();
		bricksListOfEachBrickType.put(POWER_UP, powerUpBricks);
		bricksListOfEachBrickType.put(BREAKABLE, breakableBricks);
		bricksListOfEachBrickType.put(UNBREAKABLE, unbreakableBricks);
	}
	
	// associate the integer label with string label of each brick type in Breakout Game
	private void setIntegerToStringOfEachBrickType() {
		integerToStringOfEachBrickType = new HashMap<Integer, String>();
		integerToStringOfEachBrickType.put(0, EMPTY);
		integerToStringOfEachBrickType.put(1, POWER_UP);
		integerToStringOfEachBrickType.put(2, BREAKABLE);
		integerToStringOfEachBrickType.put(3, UNBREAKABLE);
	}
	
}
