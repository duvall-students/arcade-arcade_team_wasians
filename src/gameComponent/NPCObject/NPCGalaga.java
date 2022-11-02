package gameComponent.NPCObject;

import gameComponent.ControlUnit.GalagaShip;
import javafx.geometry.Point2D;

public abstract class NPCGalaga extends GameNPC{
	
	// velocity of winged
	protected Point2D wingedVelocity;

	protected NPCGalaga(int eachRowNpcs, int npcsOffsetFromTop) {
		super(eachRowNpcs, npcsOffsetFromTop);
		final int GENERAL_WINGED_HEIGHT = GENERAL_NPC_WIDTH;
		GENERAL_NPC_HEIGHT = GENERAL_WINGED_HEIGHT;
		NPC_IMAGE_HEIGHT = GENERAL_NPC_HEIGHT;
	}
	
	public void move (double wingedYVelocity, double elapsedTime, GalagaShip ship) {
//		npc.setY(npc.getY() - wingedVelocity.getY() * elapsedTime);
		double xVal = 0;
		double yVal = wingedYVelocity;
		wingedVelocity = new Point2D(xVal, yVal);
		failureCheckForLevel(ship);
		npc.setY(npc.getY() + wingedVelocity.getY() * elapsedTime);
	}
	
	protected void failureCheckForLevel(GalagaShip ship) {
		if (ship.getBulletSpawnLoc().getY() <= npc.getY() + npc.getFitHeight()) {
//			System.out.println("Oh, NO! You lose! >w<");
			System.out.println("Oh, NO! You lose! （。>︿<）_θ");
//			System.out.println("Oh, NO! You lose! ...(｡•ˇ‸ˇ•｡) ...");
		}
	}
	
}
