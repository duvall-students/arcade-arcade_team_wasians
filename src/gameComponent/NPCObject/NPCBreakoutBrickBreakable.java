package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCBreakoutBrickBreakable.java
 * 
 */

public class NPCBreakoutBrickBreakable extends NPCBreakout {
	
	public NPCBreakoutBrickBreakable(int eachRowBricks, int bricksOffsetFromTop, String imageSource) {
		super(eachRowBricks, bricksOffsetFromTop);
		npcImageSrc = imageSource;
	}
	
}
