package gamePlaySystem.LevelSystem;

import javafx.scene.Group;

import gameComponent.NPCObject.NPCBreakoutBrickBreakable;
import gameComponent.NPCObject.NPCBreakoutBrickPowerUp;
import gameComponent.MovableObject.BallBreakout;
import gameComponent.NPCObject.GameNPC;
import gamePlaySystem.Player;

/**
 * @author Xu Yan
 * 
 * BreakoutLevel_1.java
 * 
 */

public class BreakoutLevel_1 extends BreakoutLevels {

	// display of bricks in Level_1
	private static final int BRICKS_Y_OFFSET = 40;
	private static final int LEVEL = 1;
	private final int [][] LAYOUT_L1 = {
			{0, 2, 2, 2, 2, 2, 0},
			{2, 2, 2, 0, 2, 2, 2},
			{2, 2, 0, 1, 0, 2, 2},
			{2, 2, 2, 0, 2, 2, 2},
			{0, 2, 2, 2, 2, 2, 0},
	};
	
	public BreakoutLevel_1() {
		super(BRICKS_Y_OFFSET, LEVEL);
		STARTING_POSITION = 1.8/4.0;
		allowedHealth = 5;
		gameNPCLayout = LAYOUT_L1;
		eachRowNPCs = LAYOUT_L1[0].length;
	}
	
	// load the brick on the screen
    @Override
	protected void generateBricks(int col, int row, Group root, String brickType) {
		final String POWER_UP_BRICK_IMAGE_SOURCE = "resources/speedballpower.png";
		final String BREAKABLE_BRICK_IMAGE_SOURCE = "resources/brick10.gif";
		if (brickType.equals(POWER_UP)) {
			npc = new NPCBreakoutBrickPowerUp(eachRowNPCs, npcsOffsetFromTop, POWER_UP_BRICK_IMAGE_SOURCE);
		} else {
			npc = new NPCBreakoutBrickBreakable(eachRowNPCs, npcsOffsetFromTop, BREAKABLE_BRICK_IMAGE_SOURCE);
		}
		npc.setNPC(col, row);
		bricksListOfEachBrickType.get(brickType).add(npc);
		allNPCs.add(npc);
		root.getChildren().add(npc.getNPC());
	}
    
    // check the winning condition at this level
    @Override
	protected void winCheckForLevel() {
		final int ALL_CLEAR = 0;
		boolean isLevelAccomplished = breakableBricks.size() == ALL_CLEAR;
		if (isLevelAccomplished) {
			isWinnerInLevel = true;
			winningMessage();
		}
	}
	
}
	