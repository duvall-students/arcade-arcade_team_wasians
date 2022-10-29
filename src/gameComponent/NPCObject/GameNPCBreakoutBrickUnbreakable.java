package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * GameNPCBreakoutBrickUnbreakable.java
 * 
 */

public class GameNPCBreakoutBrickUnbreakable extends GameNPC {
	
	// protected GameNPCBreakoutBrickUnbreakable()
	public GameNPCBreakoutBrickUnbreakable(int brickRows, int eachRowBricks, int bricksOffsetFromTop, int unbreakableImgNum) {
		// 1. super() parameters: (brickRows, eachRowBricks, bricksOffsetFromTop, brickType)
		// 2. brickType is from one of these types: {"power-up", "breakable", "unbreakable"}
		super(brickRows, eachRowBricks, bricksOffsetFromTop, "unbreakable");
		setUnbreakableBricksImageNumber(unbreakableImgNum);
	}
	
}
