package gameComponent.NPCObject;

import javafx.geometry.Point2D;

/**
 * @author Xu Yan
 * 
 * NPCGalagaWingedPowerUp.java
 * 
 */

public class NPCGalagaWingedPowerUp extends NPCGalaga {
	
	public NPCGalagaWingedPowerUp(int eachRowWingeds, int wingedsOffsetFromTop, String imageSource) {
		super(eachRowWingeds, wingedsOffsetFromTop);
		npcImageSrc = imageSource;
//		wingedVelocity = new Point2D(0, -20);
	}
	
//	public void move (double elapsedTime) {
//		npc.setY(npc.getY() + wingedVelocity.getY() * elapsedTime);
//	}
	
}
