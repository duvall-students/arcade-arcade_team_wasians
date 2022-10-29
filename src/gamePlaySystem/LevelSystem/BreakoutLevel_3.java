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
 * GameBreakoutLevel_3.java
 * 
 */

public class BreakoutLevel_3 extends GameLevel {
	
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
	
    // create the brick based on its type
	protected void createBricks(Group root) {
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
    private void generateBricks(int col, int row, Group root, String brickType) {
    	final String POWER_UP_BRICK_IMAGE_SOURCE = "resources/speedballpower.png";
    	final String BREAKABLE_BRICK_IMAGE_SOURCE = "resources/brick5.gif";
    	final String UNBREAKABLE_BRICK_IMAGE_SOURCE = "resources/brick9.gif";
    	
    	if (brickType.equals(POWER_UP)) {
    		npc = new NPCBreakoutBrickPowerUp(eachRowNPCs, bricksOffsetFromTop, POWER_UP_BRICK_IMAGE_SOURCE);
    	} else if (brickType.equals(UNBREAKABLE)) {
    		npc = new NPCBreakoutBrickUnbreakable(eachRowNPCs, bricksOffsetFromTop, UNBREAKABLE_BRICK_IMAGE_SOURCE);
    	} else {
    		npc = new NPCBreakoutBrickBreakable(eachRowNPCs, bricksOffsetFromTop, BREAKABLE_BRICK_IMAGE_SOURCE);
    	}
    	npc.setNPC(col, row);
    	bricksListOfEachBrickType.get(brickType).add(npc);
    	allNPCs.add(npc);
    	root.getChildren().add(npc.getNPC());
    }
    
    // deal with the collision of the ball and bricks
    protected void collideWithBricks(Group root, BallBreakout ball, Player player) {
    	final boolean BREAKABLE = true;
    	final boolean UNBREAKABLE = false;
    	final boolean isPaddle = false;
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
    			ball.bounceOnRectangle(npc.getNPCImageView(), isPaddle);
    		}
    		winCheckForLevel();
    	}
    }

}
