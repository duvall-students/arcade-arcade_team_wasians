package gamePlaySystem.LevelSystem;

import java.util.HashMap;
import java.util.function.Supplier;

import javafx.scene.Group;
import javafx.stage.Stage;

import gameComponent.MovableObject.BallBreakout;
import gameComponent.MovableObject.BulletGalaga;
import gamePlaySystem.Player;

/**
 * @author Xu Yan
 * 
 * GalagaLevelControl.java
 * 
 */

public class GalagaLevelControl extends GameLevelControl {

	public GalagaLevelControl(Group root, int levelNum) {
		super(root, levelNum);
		levelUpNum = 1;
	}
	
	public void getElementsCollisionInEachLevel(Stage myStage, Group root, BulletGalaga bullet, Player player, int levelNum) {
		try {
			if (levelNum == 1) {
				((GalagaLevel_1) gameLevel).collideWithNPCs(root, bullet, player);
			} else if (levelNum == 2) {
				((GalagaLevel_2) gameLevel).collideWithNPCs(root, bullet, player);
			} else if (levelNum == 3) {
				((GalagaLevel_3) gameLevel).collideWithNPCs(root, bullet, player);
			}
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

	// calling default constructors of GalagaLevels
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
