package gameComponent.NPCObject;

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
	
	protected void move (double elapsedTime) {
		npc.setY(npc.getY() + wingedVelocity.getY() * elapsedTime);
	}

}
