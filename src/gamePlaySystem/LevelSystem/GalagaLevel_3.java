package gamePlaySystem.LevelSystem;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;

import gameComponent.MovableObject.BallBreakout;
import gameComponent.NPCObject.GameNPC;
import gameComponent.NPCObject.NPCGalagaWingedGreen;
import gameComponent.NPCObject.NPCGalagaWingedPowerUp;
import gameComponent.NPCObject.NPCGalagaWingedRed;
import gameComponent.NPCObject.NPCGalagaWingedYellow;
import gamePlaySystem.Player;

/**
 * @author Xu Yan
 * 
 * GalagaLevel_3.java
 * 
 */

public class GalagaLevel_3 extends GalagaLevels {

	private static final int BRICKS_Y_OFFSET = 10;
	private static final int LEVEL = 1;
	private List<GameNPC> allNonPowerUpWingeds;
	// the layout has row: 7, column: 18
	private int[][] LAYOUT_L3 = {
			{0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 1, 0, 4, 2, 2, 2, 2, 4, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 1, 0, 0, 3, 4, 2, 2, 4, 3, 0, 0, 1, 0, 0, 0},
			{0, 0, 0, 0, 3, 4, 3, 3, 3, 3, 3, 3, 4, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0},
			{0, 1, 0, 4, 3, 4, 4, 4, 2, 2, 4, 4, 4, 3, 4, 0, 0, 0},
			{0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0},
	};

	public GalagaLevel_3() {
		// super parameters: (brickRows, eachRowBrick, unbreakableBricksQty, emptySpaceQty, bricksYOffset)
		super(BRICKS_Y_OFFSET, LEVEL);
		allNonPowerUpWingeds = new ArrayList<>();
		allowedHealth = 3;
		gameNPCLayout = LAYOUT_L3;
		eachRowNPCs = LAYOUT_L3[0].length;
	}
	
	// load the winged on the screen
	@Override
	protected void generateWingeds(int col, int row, Group root, String wingedType) {
		final String POWER_UP_WINGED_IMAGE_SOURCE = "resources/scorepower.png";
		final String YELLOW_WINGED_IMAGE_SOURCE = "resources/winged1.png";
		final String RED_WINGED_IMAGE_SOURCE = "resources/winged2.png";
		final String GREEN_WINGED_IMAGE_SOURCE = "resources/winged3.png";
		
		if (wingedType.equals(POWER_UP)) {
			npc = new NPCGalagaWingedPowerUp(eachRowNPCs, npcsOffsetFromTop, POWER_UP_WINGED_IMAGE_SOURCE);
		} else if (wingedType.equals(GREEN)) {
			npc = new NPCGalagaWingedGreen(eachRowNPCs, npcsOffsetFromTop, GREEN_WINGED_IMAGE_SOURCE);
		} else if (wingedType.equals(RED)) {
			npc = new NPCGalagaWingedRed(eachRowNPCs, npcsOffsetFromTop, RED_WINGED_IMAGE_SOURCE);
		} else if (wingedType.equals(YELLOW)) {
			npc = new NPCGalagaWingedYellow(eachRowNPCs, npcsOffsetFromTop, YELLOW_WINGED_IMAGE_SOURCE);
		}
		npc.setNPC(col, row);
		wingedsListOfEachWingedType.get(wingedType).add(npc);
		allNPCs.add(npc);
		if (!wingedType.equals(POWER_UP)) {
			allNonPowerUpWingeds.add(npc);
		}
		root.getChildren().add(npc.getNPC());
	}
	
	// deal with the collision of the bullet and winged
	@Override
	protected void collideWithNPCs(Group root, BallBreakout ball, Player player) {
		for (GameNPC npc: allNPCs) {
			if (ball.getView().getBoundsInParent().intersects(npc.getNPC().getBoundsInParent())) {
				allNPCs.remove(npc);
				if (allNonPowerUpWingeds.contains(npc)) {
					allNonPowerUpWingeds.remove(npc);
				}
				root.getChildren().remove(npc.getNPC());
				player.addScore(1);
				if (powerUpWinged.contains(npc)) {
				}
			}
			winCheckForLevel();
		}
	}
	
}
