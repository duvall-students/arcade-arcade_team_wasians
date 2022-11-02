package gamePlaySystem.LevelSystem;

import javafx.scene.Group;

import java.util.Collection;

import gameComponent.ControlUnit.GalagaShip;
import gameComponent.MovableObject.BallBreakout;
import gameComponent.MovableObject.BulletGalaga;
import gameComponent.NPCObject.GameNPC;
import gameComponent.NPCObject.NPCGalaga;
import gameComponent.NPCObject.NPCGalagaWingedPowerUp;
import gameComponent.NPCObject.NPCGalagaWingedYellow;
import gamePlaySystem.Player;
import games.Galaga;

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
			{0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0},
			{0, 0, 0, 0, 4, 4, 4, 1, 4, 4, 4, 4, 0, 0, 0, 0},
			{0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0},
			{0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 0, 0, 0},
	};
	
	private boolean collided;

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
	protected void collideWithNPCs(Group root, BulletGalaga bullet, Player player, Collection<BulletGalaga> bulletList) {
		for (GameNPC npc: allNPCs) {
			if (bullet.getView().getBoundsInParent().intersects(npc.getNPC().getBoundsInParent())) {
				yellowWinged.remove(npc);
				allNPCs.remove(npc);
				root.getChildren().remove(npc.getNPC());
				root.getChildren().remove(bullet.getView());
//				root.getChildren().remove(bullet);
				player.addScore(1);
				if (powerUpWinged.contains(npc)) {
					
				}
				bullet.setVelocity(0, 0);
				
			}
			winCheckForLevel();
		}
	}
	
//	protected void moveWinged(double elapsedTime, GalagaShip ship) {
//		for (GameNPC npc: allNPCs) {
//			((NPCGalaga) npc).move(elapsedTime, ship);
//		}
//	}

    // check the winning condition at this level
    @Override
	protected void winCheckForLevel() {
		final int ALL_CLEAR = 0;
		boolean isLevelAccomplished = yellowWinged.size() == ALL_CLEAR;
		if (isLevelAccomplished) {
			isWinnerInLevel = true;
			winningMessage();
		}
	}
    
}
