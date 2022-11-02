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
 * GalagaLevel_1.java
 * 
 */

public class GalagaLevel_1 extends GalagaLevels {

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
		allowedHealth = 5;
		gameNPCLayout = LAYOUT_L1;
		eachRowNPCs = LAYOUT_L1[0].length;
	}
    
	// load the winged on the screen
    @Override
	protected void generateWingeds(int col, int row, Group root, String wingedType) {
		final String POWER_UP_WINGED_IMAGE_SOURCE = "resources/scorepower.png";
		final String YELLOW_WINGED_IMAGE_SOURCE = "resources/winged1.png";
		
		if (wingedType.equals(POWER_UP)) {
			npc = new NPCGalagaWingedPowerUp(eachRowNPCs, npcsOffsetFromTop, POWER_UP_WINGED_IMAGE_SOURCE);
		} else {
			npc = new NPCGalagaWingedYellow(eachRowNPCs, npcsOffsetFromTop, YELLOW_WINGED_IMAGE_SOURCE);
		}
		npc.setNPC(col, row);
		wingedsListOfEachWingedType.get(wingedType).add(npc);
		allNPCs.add(npc);
		root.getChildren().add(npc.getNPC());
	}
	
	// deal with the collision of the bullet and winged
    @Override
	protected void collideWithNPCs(Group root, BallBreakout ball, Player player) {
		for (GameNPC npc: allNPCs) {
			if (ball.getView().getBoundsInParent().intersects(npc.getNPC().getBoundsInParent())) {
				yellowWinged.remove(npc);
				allNPCs.remove(npc);
				root.getChildren().remove(npc.getNPC());
				player.addScore(1);
				if (powerUpWinged.contains(npc)) {
				}
			}
			winCheckForLevel();
		}
	}

	@Override
	protected void winCheckForLevel() {
		
	}
	
}
