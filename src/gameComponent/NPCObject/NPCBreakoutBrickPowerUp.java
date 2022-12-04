package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCBreakoutBrickPowerUp.java
 * Implement setting the related properties of power-up bricks in the Breakout.
 * 
 */

public class NPCBreakoutBrickPowerUp extends NPCBreakout {
	
	public NPCBreakoutBrickPowerUp(int eachRowBricks, int bricksOffsetFromTop, String imageSource) {
		super(eachRowBricks, bricksOffsetFromTop);
		npcImageSrc = imageSource;
	}
	
}
