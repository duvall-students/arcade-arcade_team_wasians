package gamePlaySystem.LevelSystem;

import javafx.scene.Group;

import java.util.Collection;

import gameComponent.ControlUnit.GalagaShip;
import gameComponent.MovableObject.BulletGalaga;
import gameComponent.NPCObject.GameNPC;
import gameComponent.NPCObject.NPCGalaga;
import gameComponent.NPCObject.NPCGalagaWingedPowerUp;
import gameComponent.NPCObject.NPCGalagaWingedYellow;
import gamePlaySystem.Player;
import gamePlaySystem.PlayerMessaging;

/**
 * @author Xu Yan
 * 
 * GalagaLevel_1.java
 * Implement setting the related properties of the Level_1 in the Galaga.
 * 
 */

public class GalagaLevel_1 extends GalagaLevels {

	// display of winged in Level_1
	private static final int BRICKS_Y_OFFSET = 30;
	private static final int LEVEL = 1;
	// the layout has row: 5, column: 16; power-up: 3
	private int[][] LAYOUT_L1 = {
			{0, 0, 0, 0, 0, 0, 4, 4, 4, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0},
			{0, 0, 0, 0, 4, 4, 4, 1, 4, 4, 4, 4, 0, 0, 0, 0},
			{0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0},
			{0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 0, 0, 0},
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
	protected void collideWithNPCs(Group root, BulletGalaga bullet, Player player, Collection<BulletGalaga> bulletList, PlayerMessaging playerMessaging) {
		for (GameNPC npc: allNPCs) {
			if (bullet.getView().getBoundsInParent().intersects(npc.getNPC().getBoundsInParent())) {
				yellowWinged.remove(npc);
				allNPCs.remove(npc);
				bulletList.remove(bullet);
				root.getChildren().remove(npc.getNPC());
				root.getChildren().remove(bullet.getView());
				player.addScore(1);
				playerMessaging.displayScore(player);
				if (powerUpWinged.contains(npc)) {
					player.addScore(5);
					playerMessaging.displayScore(player);
				}
			}
			winCheckForLevel();
		}
	}
	
	// set the moving properties of the winged
	@Override
	protected void moveWinged(double elapsedTime, GalagaShip ship) {
		double wingedYVelocity = 10;
		for (GameNPC npc: allNPCs) {
			((NPCGalaga) npc).move(wingedYVelocity, elapsedTime, ship);
			
		}
	}
	
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
