package gamePlaySystem.LevelSystem;

import java.util.HashMap;
import java.util.List;

import gameComponent.MovableObject.BallBreakout;
import gameComponent.NPCObject.GameNPC;
import gamePlaySystem.Player;
import javafx.scene.Group;

/**
 * @author Xu Yan
 * 
 * BreakoutLevels.java
 * 
 */

public abstract class BreakoutLevels extends GameLevel {

	// variables / properties about the level in Breakout Game
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
		setBricksListOfEachBrickType();
		setPowerUpBrickSettingsInLevels();
		setIntegerToStringOfEachBrickType();
	}
	
	// create the brick based on its type
	@Override
	protected void createNPCs(Group root) {
		for (int row = 0; row < gameNPCLayout.length; row++) {
    		for (int col = 0; col < gameNPCLayout[row].length; col++) {
    			String typeStr = integerToStringOfEachBrickType.get(gameNPCLayout[row][col]);
    			if (!typeStr.equals(EMPTY)) {
    				generateBricks(col, row, root, typeStr);
    			}
    		}
    	}
	}
	
	protected abstract void generateBricks(int col, int row, Group root, String brickType);
	
	// deal with the collision of the ball and bricks
	@Override
	protected void collideWithNPCs(Group root, BallBreakout ball, Player player) {
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
				}
				if (powerUpBricks.contains(npc)) {
					ball.powerUpBall();
				}
				ball.bounceOnRectangle(npc.getNPCImageView(), isNotPaddle);
			}
			winCheckForLevel();
		}
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
	
	protected double getBallStartingPositionNow() {
		return STARTING_POSITION;
	}

}
