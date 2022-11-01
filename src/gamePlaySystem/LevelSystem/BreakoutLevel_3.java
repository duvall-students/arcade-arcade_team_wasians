package gamePlaySystem.LevelSystem;

import javafx.scene.Group;

import gameComponent.MovableObject.BallBreakout;
import gameComponent.NPCObject.GameNPC;
import gameComponent.NPCObject.NPCBreakoutBrickBreakable;
import gameComponent.NPCObject.NPCBreakoutBrickPowerUp;
import gameComponent.NPCObject.NPCBreakoutBrickUnbreakable;
import gamePlaySystem.Player;

/**
 * @author Xu Yan
 * 
 * BreakoutLevel_3.java
 * 
 */

public class BreakoutLevel_3 extends BreakoutLevels {
	
	// display of bricks in Level_3
	private static final int BRICKS_Y_OFFSET = 20;
	private static final int LEVEL = 3;
	private final int [][] LAYOUT_L3 = {
			{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 
			{3, 3, 2, 0, 2, 1, 2, 0, 2, 3, 3},
			{3, 2, 2, 1, 2, 0, 2, 1, 2, 2, 3},
			{3, 2, 2, 2, 0, 2, 0, 2, 2, 2, 3},
			{3, 2, 0, 2, 2, 2, 2, 2, 0, 2, 3},
			{3, 2, 2, 2, 2, 3, 2, 2, 2, 2, 3},
			{3, 0, 2, 0, 2, 2, 2, 0, 2, 0, 3},
			{3, 3, 2, 2, 2, 0, 2, 2, 2, 3, 3},
			{3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
			{3, 2, 0, 2, 0, 2, 0, 2, 0, 2, 3}, 
	};
	
	public BreakoutLevel_3() {
		super(BRICKS_Y_OFFSET, LEVEL);
		allowedHealth = 3;
		gameNPCLayout = LAYOUT_L3;
		eachRowNPCs = LAYOUT_L3[0].length;
	}
	
	// load the brick on the screen
	@Override
    protected void generateBricks(int col, int row, Group root, String brickType) {
    	final String POWER_UP_BRICK_IMAGE_SOURCE = "resources/speedballpower.png";
    	final String BREAKABLE_BRICK_IMAGE_SOURCE = "resources/brick5.gif";
    	final String UNBREAKABLE_BRICK_IMAGE_SOURCE = "resources/brick9.gif";
    	
    	if (brickType.equals(POWER_UP)) {
    		npc = new NPCBreakoutBrickPowerUp(eachRowNPCs, npcsOffsetFromTop, POWER_UP_BRICK_IMAGE_SOURCE);
    	} else if (brickType.equals(UNBREAKABLE)) {
    		npc = new NPCBreakoutBrickUnbreakable(eachRowNPCs, npcsOffsetFromTop, UNBREAKABLE_BRICK_IMAGE_SOURCE);
    	} else {
    		npc = new NPCBreakoutBrickBreakable(eachRowNPCs, npcsOffsetFromTop, BREAKABLE_BRICK_IMAGE_SOURCE);
    	}
    	npc.setNPC(col, row);
    	bricksListOfEachBrickType.get(brickType).add(npc);
    	allNPCs.add(npc);
    	root.getChildren().add(npc.getNPC());
    }
	
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
