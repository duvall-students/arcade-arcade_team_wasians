package gameComponent.NPCObject;

import javafx.geometry.Point2D;

/**
 * @author Xu Yan
 * 
 * NPCGalagaWingedGreen.java
 * 
 */

public class NPCGalagaWingedGreen extends GameNPC {

	public NPCGalagaWingedGreen(int eachRowWingeds, int wingedsOffsetFromTop, String imageSource) {
		super(eachRowWingeds, wingedsOffsetFromTop);
		final int GENERAL_WINGED_HEIGHT;
		GENERAL_WINGED_HEIGHT = GENERAL_NPC_WIDTH;
		GENERAL_NPC_HEIGHT = GENERAL_WINGED_HEIGHT;
		NPC_IMAGE_HEIGHT = GENERAL_NPC_HEIGHT;
		npcImageSrc = imageSource;
		wingedVelocity = new Point2D(0, -20);
	}
	
	protected void move (double elapsedTime) {
		npc.setY(npc.getY() + wingedVelocity.getY() * elapsedTime);
	}
	
}
