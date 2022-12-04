package gameComponent.NPCObject;

import gameComponent.ControlUnit.GalagaShip;

import javafx.geometry.Point2D;

/**
 * @author Xu Yan
 * 
 * NPCGalaga.java
 * Implement setting the related properties of different types of flying objects in the Galaga.
 * 
 */

public abstract class NPCGalaga extends GameNPC{
	
	// velocity properties of winged
	protected Point2D wingedVelocity;

	protected NPCGalaga(int eachRowNpcs, int npcsOffsetFromTop) {
		super(eachRowNpcs, npcsOffsetFromTop);
		final int GENERAL_WINGED_HEIGHT = GENERAL_NPC_WIDTH;
		GENERAL_NPC_HEIGHT = GENERAL_WINGED_HEIGHT;
		NPC_IMAGE_HEIGHT = GENERAL_NPC_HEIGHT;
	}
	
	// assign the moving velocity of the winged
	public void move (double wingedYVelocity, double elapsedTime, GalagaShip ship) {
		double xVal = 0;
		double yVal = wingedYVelocity;
		wingedVelocity = new Point2D(xVal, yVal);
		failureCheckForLevel(ship);
		npc.setY(npc.getY() + wingedVelocity.getY() * elapsedTime);
	}
	
	// detect the failure of the game (when any NPC moves below the player's ship)
	protected void failureCheckForLevel(GalagaShip ship) {
		if (ship.getBulletSpawnLoc().getY() <= npc.getY() + npc.getFitHeight()) {
			System.out.println("Oh, NO! You lose! （。>︿<）_θ");
		}
	}
	
}
