package gamePlaySystem.LevelSystem;

/**
 * @author Xu Yan
 * 
 * GameBreakoutLevel_3.java
 * 
 */

public class GameBreakoutLevel_3 extends GameLevel {
	
	private static final int BRICK_ROWS = 10;
	private static final int EACH_ROW_BRICK = 11;
	private static final int UNBREAKABLE_BRICK_QTY = 0; // 9
	private static final int POWER_UP_BRICK_QTY = 3;
	private static final int EMPTY_SPACE_QTY = 10;
	private static final int BRICKS_Y_OFFSET = 20;
	private static final int LEVEL = 3;
	private final int [][] LAYOUT_L3 = {
			{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 
			{3, 3, 2, 0, 2, 1, 2, 0, 2, 3, 3},
			{3, 2, 2, 1, 2, 0, 2, 1, 2, 2, 3},
			{3, 2, 2, 2, 0, 2, 0, 2, 2, 2, 3},
			{3, 2, 0, 2, 2, 2, 2, 2, 0, 2, 3},
			{3, 2, 2, 2, 2, 3, 2, 2, 2, 2, 3},
			{3, 0, 2, 0, 2, 2, 2, 0, 2, 0, 3},
			{3, 3, 2, 2, 2, 0, 2, 2, 2, 3, 3},
			{3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
			{3, 2, 0, 2, 0, 2, 0, 2, 0, 2, 3}, 
	};
	
	public GameBreakoutLevel_3() {
		// super() parameters: (brickRows, eachRowBricks, unbreakableBricksQty, powerUpBricksQty, emptySpaceQty, bricksOffsetFromTop, level)
		super(BRICK_ROWS, EACH_ROW_BRICK, UNBREAKABLE_BRICK_QTY, POWER_UP_BRICK_QTY, EMPTY_SPACE_QTY, BRICKS_Y_OFFSET, LEVEL);
		allowedHealth = 3;
		bricksLayout = LAYOUT_L3;
		setBricksNumByBrickType("empty");
		setBricksNumByBrickType("power-up");
		setBricksNumByBrickType("unbreakable");
	}

}
