package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCBreakoutBrickUnbreakable.java
 * 
 */

public class NPCBreakoutBrickUnbreakable extends NPCBreakout {
	
	public NPCBreakoutBrickUnbreakable(int eachRowBricks, int bricksOffsetFromTop, String imageSource) {
		super(eachRowBricks, bricksOffsetFromTop);
		npcImageSrc = imageSource;
	}
	
}
