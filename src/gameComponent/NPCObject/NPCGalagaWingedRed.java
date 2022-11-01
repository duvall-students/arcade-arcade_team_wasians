package gameComponent.NPCObject;

import javafx.geometry.Point2D;

/**
 * @author Xu Yan
 * 
 * NPCGalagaWingedRed.java
 * 
 */

public class NPCGalagaWingedRed extends NPCGalaga {
	
	public NPCGalagaWingedRed(int eachRowWingeds, int wingedsOffsetFromTop, String imageSource) {
		super(eachRowWingeds, wingedsOffsetFromTop);
		npcImageSrc = imageSource;
		wingedVelocity = new Point2D(0, -20);
	}

//	protected void move (double elapsedTime) {
//		npc.setY(npc.getY() + wingedVelocity.getY() * elapsedTime);
//	}
	
}
