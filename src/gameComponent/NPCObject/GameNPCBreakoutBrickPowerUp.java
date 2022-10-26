package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * GameNPCBreakoutBrickPowerUp.java
 * 
 */

public class GameNPCBreakoutBrickPowerUp extends GameNPC {
	
	// protected GameNPCBreakoutBrickPowerUp()
	public GameNPCBreakoutBrickPowerUp(int brickRows, int eachRowBricks, int bricksOffsetFromTop, String powerUpType) {
		// 1. super() parameters: (brickRows, eachRowBricks, bricksOffsetFromTop, brickType)
		// 2. brickType is from one of these types: {"power-up", "breakable", "unbreakable"}
		super(brickRows, eachRowBricks, bricksOffsetFromTop, "power-up");
		setPowerUpBricksImageType(powerUpType);
	}
	
}
