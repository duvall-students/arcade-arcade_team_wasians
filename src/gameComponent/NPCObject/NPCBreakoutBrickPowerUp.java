package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCBreakoutBrickPowerUp.java
 * 
 */

public class NPCBreakoutBrickPowerUp extends NPCBreakout {
	
	public NPCBreakoutBrickPowerUp(int eachRowBricks, int bricksOffsetFromTop, String imageSource) {
		super(eachRowBricks, bricksOffsetFromTop);
		npcImageSrc = imageSource;
	}
	
}
