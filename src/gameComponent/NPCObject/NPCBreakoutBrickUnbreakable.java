package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCBreakoutBrickUnbreakable.java
 * Implement setting the related properties of unbreakable bricks in the Breakout.
 * 
 */

public class NPCBreakoutBrickUnbreakable extends NPCBreakout {
	
	public NPCBreakoutBrickUnbreakable(int eachRowBricks, int bricksOffsetFromTop, String imageSource) {
		super(eachRowBricks, bricksOffsetFromTop);
		npcImageSrc = imageSource;
	}
	
}
