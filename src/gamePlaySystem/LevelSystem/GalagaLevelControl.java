package gamePlaySystem.LevelSystem;

import java.util.HashMap;
import java.util.function.Supplier;

import gameComponent.MovableObject.BallBreakout;
import gamePlaySystem.Player;
import javafx.scene.Group;
import javafx.stage.Stage;

public class GalagaLevelControl extends GameLevelControl {

	public GalagaLevelControl(Group root, int levelNum) {
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
	}

	@Override
	protected void setUpLevelToConstructorNoParameterMap() {
		final int LEVEL_1 = 1;
		final int LEVEL_2 = 2;
		final int LEVEL_3 = 3;
		levelToConstructorNoParameter = new HashMap<Integer, Supplier<GameLevel>>();
		levelToConstructorNoParameter.put(LEVEL_1, GalagaLevel_1::new);
		levelToConstructorNoParameter.put(LEVEL_2, GalagaLevel_2::new);
		levelToConstructorNoParameter.put(LEVEL_3, GalagaLevel_3::new);
	}

}
