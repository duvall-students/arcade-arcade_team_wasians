package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCBreakoutBrickBreakable.java
 * 
 */

public class NPCBreakoutBrickBreakable extends GameNPC {
	
	public NPCBreakoutBrickBreakable(int eachRowBricks, int bricksOffsetFromTop, String imageSource) {
		super(eachRowBricks, bricksOffsetFromTop);
		final int GENERAL_BRICK_HEIGHT = 8;
		GENERAL_NPC_HEIGHT = GENERAL_BRICK_HEIGHT;
		NPC_IMAGE_HEIGHT = GENERAL_BRICK_HEIGHT;
		npcImageSrc = imageSource;
	}
	
}
