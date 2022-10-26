package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * GameNPCBreakoutBrickBreakable.java
 * 
 */

public class GameNPCBreakoutBrickBreakable extends GameNPC {
	
	// protected GameNPCBreakoutBrickBreakable()
	public GameNPCBreakoutBrickBreakable(int brickRows, int eachRowBricks, int bricksOffsetFromTop, int breakableImgNum) {
		// 1. super() parameters: (brickRows, eachRowBricks, bricksOffsetFromTop, brickType)
		// 2. brickType is from one of these types: {"power-up", "breakable", "unbreakable"}
		super(brickRows, eachRowBricks, bricksOffsetFromTop, "breakable");
		setBreakableBricksImageNumber(breakableImgNum);
	}
	
}
