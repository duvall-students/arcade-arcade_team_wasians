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
		final int GENERAL_BRICK_HEIGHT = 8;
		GENERAL_NPC_HEIGHT = GENERAL_BRICK_HEIGHT;
		NPC_IMAGE_HEIGHT = GENERAL_NPC_HEIGHT;
		npcImageSrc = imageSource;
	}
	
}
