package gamePlaySystem.LevelSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.scene.Group;

import gameComponent.ControlUnit.GalagaShip;
import gameComponent.MovableObject.BulletGalaga;
import gameComponent.NPCObject.GameNPC;
import gameComponent.NPCObject.NPCGalaga;
import gameComponent.NPCObject.NPCGalagaWingedGreen;
import gameComponent.NPCObject.NPCGalagaWingedPowerUp;
import gameComponent.NPCObject.NPCGalagaWingedRed;
import gameComponent.NPCObject.NPCGalagaWingedYellow;
import gamePlaySystem.Player;

/**
 * @author Xu Yan
 * 
 * GalagaLevel_3.java
 * Implement setting the related properties of the Level_3 in the Galaga.
 * 
 */

public class GalagaLevel_3 extends GalagaLevels {

	// display of winged in Level_3
	private static final int BRICKS_Y_OFFSET = 10;
	private static final int LEVEL = 3;
	private List<GameNPC> allNonPowerUpWingeds;
	// the layout has row: 7, column: 18; power-up: 9
	private int[][] LAYOUT_L3 = {
			{1, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 1, 0, 4, 2, 2, 2, 2, 4, 0, 0, 0, 1, 0, 0},
			{1, 0, 0, 0, 0, 0, 3, 4, 2, 2, 4, 3, 0, 0, 0, 0, 0, 0},
			{0, 0, 1, 0, 3, 4, 3, 3, 3, 3, 3, 3, 4, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 1, 0},
			{0, 1, 0, 4, 3, 4, 4, 4, 2, 2, 4, 4, 4, 3, 4, 0, 0, 0},
			{0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 1},
	};

	public GalagaLevel_3() {
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
	protected void collideWithNPCs(Group root, BulletGalaga bullet, Player player, Collection<BulletGalaga> bulletList) {
		for (GameNPC npc: allNPCs) {
			if (bullet.getView().getBoundsInParent().intersects(npc.getNPC().getBoundsInParent())) {
				allNPCs.remove(npc);
				if (allNonPowerUpWingeds.contains(npc)) {
					allNonPowerUpWingeds.remove(npc);
				}
				bulletList.remove(bullet);
				root.getChildren().remove(npc.getNPC());
				root.getChildren().remove(bullet.getView());
				player.addScore(1);
				if (powerUpWinged.contains(npc)) {
					player.addScore(15);
				}
			}
			winCheckForLevel();
		}
	}
	
	// set the moving properties of the winged
	@Override
	protected void moveWinged(double elapsedTime, GalagaShip ship) {
		double wingedYVelocity = 3;
		for (GameNPC npc: allNPCs) {
			((NPCGalaga) npc).move(wingedYVelocity, elapsedTime, ship);
		}
	}
	
    // check the winning condition at this level
    @Override
	protected void winCheckForLevel() {
		final int ALL_CLEAR = 0;
		boolean isLevelAccomplished = allNonPowerUpWingeds.size() == ALL_CLEAR;
		if (isLevelAccomplished) {
			isWinnerInLevel = true;
			winningMessage();
		}
	}
	
}
