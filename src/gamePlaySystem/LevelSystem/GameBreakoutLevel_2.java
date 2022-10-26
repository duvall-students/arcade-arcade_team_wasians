package gamePlaySystem.LevelSystem;

/**
 * @author Xu Yan
 * 
 * GameBreakoutLevel_2.java
 * 
 */

public class GameBreakoutLevel_2 extends GameLevel {
	
	private static final int BRICK_ROWS = 8;
	private static final int EACH_ROW_BRICK = 9;
	private static final int UNBREAKABLE_BRICK_QTY = 0; // 7
	private static final int POWER_UP_BRICK_QTY = 2;
	private static final int EMPTY_SPACE_QTY = 5;
	private static final int BRICKS_Y_OFFSET = 25;
	private static final int LEVEL = 2;
	private final int [][] LAYOUT_L2 = {
			{0, 1, 2, 2, 2, 2, 2, 2, 0},
			{0, 2, 0, 2, 0, 2, 0, 2, 0},
			{3, 2, 2, 2, 0, 2, 2, 1, 3},
			{3, 2, 0, 2, 3, 2, 0, 2, 3},
			{3, 2, 2, 2, 3, 2, 2, 2, 3},
			{3, 2, 2, 2, 1, 2, 2, 2, 3},
			{3, 2, 2, 0, 2, 0, 2, 2, 3},
			{2, 0, 2, 2, 0, 2, 2, 0, 2},
	};
	
	public GameBreakoutLevel_2() {
		// super() parameters: (brickRows, eachRowBricks, unbreakableBricksQty, powerUpBricksQty, emptySpaceQty, bricksOffsetFromTop, level)
		super(BRICK_ROWS, EACH_ROW_BRICK, UNBREAKABLE_BRICK_QTY, POWER_UP_BRICK_QTY, EMPTY_SPACE_QTY, BRICKS_Y_OFFSET, LEVEL);
		allowedHealth = 3;
		bricksLayout = LAYOUT_L2;
		setBricksNumByBrickType("empty");
		setBricksNumByBrickType("power-up");
		setBricksNumByBrickType("unbreakable");
	}
	
}
