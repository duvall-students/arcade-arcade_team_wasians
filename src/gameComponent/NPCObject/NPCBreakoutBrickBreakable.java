package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCBreakoutBrickBreakable.java
 * Implement setting the related properties of breakable bricks in the Breakout.
 * 
 */

public class NPCBreakoutBrickBreakable extends NPCBreakout {
	
	public NPCBreakoutBrickBreakable(int eachRowBricks, int bricksOffsetFromTop, String imageSource) {
		super(eachRowBricks, bricksOffsetFromTop);
		npcImageSrc = imageSource;
	}
	
}
