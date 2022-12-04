package gamePlaySystem.LevelSystem;

import java.util.HashMap;
import java.util.function.Supplier;

import javafx.scene.Group;
import javafx.stage.Stage;

import gameComponent.MovableObject.BallBreakout;
import gamePlaySystem.Player;
import gamePlaySystem.PlayerMessaging;

/**
 * @author Xu Yan
 * 
 * BreakoutLevelControl.java
 * Implement the motion of NPC, collision between movable player objects and NPC objects, level transition, 
 * and other game and level general control properties in the Breakout.
 * 
 */

public class BreakoutLevelControl extends GameLevelControl {
	
	public BreakoutLevelControl(Group root, int levelNum) {
		super(root, levelNum);
		levelUpNum = 1;
	}
	
	// deal with the collision of the movable and immovable elements
	public void getElementsCollisionInEachLevel(Stage myStage, Group root, BallBreakout ball, Player player, PlayerMessaging playerMessaging) {
		try {
			((BreakoutLevels) gameLevel).collideWithNPCs(root, ball, player, playerMessaging);
		} catch (Exception e) {
			System.out.println("brick collided");
		}
		
		if (gameLevel.getIsWinningAtEachLevel()) {
			levelTransition(myStage, player, playerMessaging);
		}
	}
	
	// deal with the level transition when the player wins in the level
	@Override
	protected void levelTransition(Stage myStage, Player player, PlayerMessaging playerMessaging) {
		if (gameLevel.areAllLevelsPassed(levelNum)) {
			playerMessaging.displayEndMessage();
		}
		levelNum += levelUpNum;
		player.setReadytoPlay(false);
		myStage.close();
	}
	
	// calling default constructors of BreakoutLevels
	@Override
	protected void setUpLevelToConstructorNoParameterMap() {
		final int LEVEL_1 = 1;
		final int LEVEL_2 = 2;
		final int LEVEL_3 = 3;
		levelToConstructorNoParameter = new HashMap<Integer, Supplier<GameLevel>>();
		levelToConstructorNoParameter.put(LEVEL_1, BreakoutLevel_1::new);
		levelToConstructorNoParameter.put(LEVEL_2, BreakoutLevel_2::new);
		levelToConstructorNoParameter.put(LEVEL_3, BreakoutLevel_3::new);
	}
	
	// get the ball starting position
	public double getBallStartingPosition() {
		return ((BreakoutLevels) gameLevel).getBallStartingPositionNow();
	}
	
	// check if the player wins at the specific level in Breakout
	public boolean checkIsWinInEachLevel() {
//		return gameLevel.isWinnerInLevel;
		return gameLevel.getIsWinningAtEachLevel();
	}
	
}
