package gamePlaySystem.LevelSystem;

import java.util.HashMap;
import java.util.function.Supplier;

import javafx.scene.Group;
import javafx.stage.Stage;

import gameComponent.MovableObject.BallBreakout;
import gamePlaySystem.Player;

public class BreakoutLevelControl extends GameLevelControl {
	
	public BreakoutLevelControl(Group root, int levelNum) {
		super(root, levelNum);
		levelUpNum = 1;
	}
	
	@Override
	public void getElementsCollisionInEachLevel(Stage myStage, Group root, BallBreakout ball, Player player) {
		try {
			gameLevel.collideWithNPCs(root, ball, player);
		} catch (Exception e) {}
		
		levelTransition(myStage, player);
	}
	
	@Override
	protected void levelTransition(Stage myStage, Player player) {
		if (gameLevel.areAllLevelsPassed(levelNum)) {
			gameLevel.winningMessage();
			System.exit(0);
		}
		levelNum += levelUpNum;
		player.setReadytoPlay(false);
		myStage.close();
//		Breakout.start(new Stage());
	}
	
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
	
	public double getBallStartingPosition() {
		return ((BreakoutLevels) gameLevel).getBallStartingPositionNow();
	}
	
	public boolean checkIsWinInEachLevel() {
		return true;
	}
	
}
