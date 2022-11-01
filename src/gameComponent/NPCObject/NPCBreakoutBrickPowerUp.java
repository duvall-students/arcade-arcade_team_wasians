package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCBreakoutBrickPowerUp.java
 * 
 */

public class NPCBreakoutBrickPowerUp extends GameNPC {
	
	public NPCBreakoutBrickPowerUp(int eachRowBricks, int bricksOffsetFromTop, String imageSource) {
		super(eachRowBricks, bricksOffsetFromTop);
		npcImageSrc = imageSource;
	}
	
}
