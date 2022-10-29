package gamePlaySystem.LevelSystem;

import javafx.scene.Group;
import gameComponent.MovableObject.BallBreakout;
import gameComponent.NPCObject.GameNPC;
import gameComponent.NPCObject.NPCGalagaWingedPowerUp;
import gameComponent.NPCObject.NPCGalagaWingedYellow;
import gamePlaySystem.Player;

/**
 * @author Xu Yan
 * 
 * GameBreakoutLevel_1.java
 * 
 */

public class GalagaLevel_1 extends GameLevel {

	// display of winged in Level_1
	private static final int BRICKS_Y_OFFSET = 30;
	private static final int LEVEL = 1;
	// the layout has row: 5, column: 18
	private int[][] LAYOUT_L1 = {
			{0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 4, 4, 4, 1, 4, 4, 4, 4, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0},
			{0, 0, 0, 0, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 0, 0, 0, 0},
	};

	public GalagaLevel_1() {
		super(BRICKS_Y_OFFSET, LEVEL);
		STARTING_POSITION = 1.8/4.0;
		allowedHealth = 5;
		gameNPCLayout = LAYOUT_L1;
		eachRowNPCs = LAYOUT_L1[0].length;
	}
	
    // create the winged based on its type
    protected void createWingeds(Group root) {
    	for (int row = 0; row < gameNPCLayout.length; row++) {
    		for (int col = 0; col < gameNPCLayout[row].length; col++) {
    			String typeStr = integerToStringOfEachWingedType.get(gameNPCLayout[row][col]);
    			if (!typeStr.equals(EMPTY)) {
    				generateWingeds(col, row, root, typeStr);
    			}
    		}
    	}
    }
    
	// load the winged on the screen
	private void generateWingeds(int col, int row, Group root, String wingedType) {
		final String POWER_UP_WINGED_IMAGE_SOURCE = "resources/scorepower.png";
		final String YELLOW_WINGED_IMAGE_SOURCE = "resources/winged1.png";
		
		if (wingedType.equals(POWER_UP)) {
			npc = new NPCGalagaWingedPowerUp(eachRowNPCs, bricksOffsetFromTop, POWER_UP_WINGED_IMAGE_SOURCE);
		} else {
			npc = new NPCGalagaWingedYellow(eachRowNPCs, bricksOffsetFromTop, YELLOW_WINGED_IMAGE_SOURCE);
		}
		npc.setNPC(col, row);
		wingedsListOfEachWingedType.get(wingedType).add(npc);
		allNPCs.add(npc);
		root.getChildren().add(npc.getNPC());
	}
	
	// deal with the collision of the bullet and winged
	protected void collideWithWingeds(Group root, BallBreakout ball, Player player) {
		final boolean isPaddle = false;
		for (GameNPC npc: allNPCs) {
			if (ball.getView().getBoundsInParent().intersects(npc.getNPC().getBoundsInParent())) {
				yellowWinged.remove(npc);
				allNPCs.remove(npc);
				root.getChildren().remove(npc.getNPC());
				player.addScore(1);
				if (powerUpWinged.contains(npc)) {
//					ball.powerUpBall();
				}
			}
			winCheckForLevel();
		}
	}
	
}
