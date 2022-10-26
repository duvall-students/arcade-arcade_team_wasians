package gamePlaySystem.LevelSystem;

/**
 * @author Xu Yan
 * 
 * GameBreakoutLevel_1.java
 * 
 */

public class GameBreakoutLevel_1 extends GameLevel {

	private static final int BRICK_ROWS = 6;
	private static final int EACH_ROW_BRICK = 7;
	private static final int UNBREAKABLE_BRICK_QTY = 0; // 5
	private static final int POWER_UP_BRICK_QTY = 1;
	private static final int EMPTY_SPACE_QTY = 0;
	private static final int BRICKS_Y_OFFSET = 40;
	private static final int LEVEL = 1;
	private final int [][] LAYOUT_L1 = {
			{0, 2, 2, 2, 2, 2, 0},
			{2, 2, 2, 0, 2, 2, 2},
			{2, 2, 0, 1, 0, 2, 2},
			{2, 2, 2, 0, 2, 2, 2},
			{0, 2, 2, 2, 2, 2, 0},
	};

	public GameBreakoutLevel_1() {
		// super parameters: (brickRows, eachRowBrick, unbreakableBricksQty, emptySpaceQty, bricksYOffset)
		super(BRICK_ROWS, EACH_ROW_BRICK, UNBREAKABLE_BRICK_QTY, POWER_UP_BRICK_QTY, EMPTY_SPACE_QTY, BRICKS_Y_OFFSET, LEVEL);
		STARTING_POSITION = 1.8/4.0;
		allowedHealth = 5;
		bricksLayout = LAYOUT_L1;
		setBricksNumByBrickType("power-up");
		setBricksNumByBrickType("unbreakable");
	}
	
}
