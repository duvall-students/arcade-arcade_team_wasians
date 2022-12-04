package gamePlaySystem.LevelSystem;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Supplier;

import javafx.scene.Group;
import javafx.stage.Stage;

import gameComponent.ControlUnit.GalagaShip;
import gameComponent.MovableObject.BulletGalaga;
import gamePlaySystem.Player;
import gamePlaySystem.PlayerMessaging;

/**
 * @author Xu Yan
 * 
 * GalagaLevelControl.java
 * Implement the motion of NPC, collision between movable player objects and NPC objects, level transition, 
 * and other game and level general control properties in the Galaga.
 * 
 */

public class GalagaLevelControl extends GameLevelControl {
	
	final int LEVEL_1 = 1;
	final int LEVEL_2 = 2;
	final int LEVEL_3 = 3;

	public GalagaLevelControl(Group root, int levelNum) {
		super(root, levelNum);
		levelUpNum = 1;
	}
	
	//
	public void getElementsCollisionInEachLevel(Stage myStage, Group root, BulletGalaga bullet, Player player, int levelNum, Collection<BulletGalaga> bulletList, GalagaShip ship, PlayerMessaging playerMessaging) {
		try {
			if (levelNum == LEVEL_1) {
				((GalagaLevel_1) gameLevel).collideWithNPCs(root, bullet, player, bulletList, playerMessaging);
			} else if (levelNum == LEVEL_2) {
				((GalagaLevel_2) gameLevel).collideWithNPCs(root, bullet, player, bulletList);
			} else if (levelNum == LEVEL_3) {
				((GalagaLevel_3) gameLevel).collideWithNPCs(root, bullet, player, bulletList);
			}
		} catch (Exception e) {
			System.out.println("winged collided");
		}
		
		if (gameLevel.getIsWinningAtEachLevel()) {
			levelTransition(myStage, player, playerMessaging);
		}
		
//		levelTransition(myStage, player);
	}
	
	// deal with the level transition if the player achieves the winning condition in the specific level
	@Override
	protected void levelTransition(Stage myStage, Player player, PlayerMessaging playerMessaging) {
		if (gameLevel.areAllLevelsPassed(levelNum)) {
			playerMessaging.displayEndMessage();
			//System.exit(0);
		}
		levelNum += levelUpNum;
		player.setReadytoPlay(false);
		myStage.close();
	}
	
	// set the moving properties of the winged
	public void getWingedMove(double elapsedTime, GalagaShip ship) {
		double wingedYVelocityOption = elapsedTime;
		if (levelNum == LEVEL_1) {
			((GalagaLevel_1) gameLevel).moveWinged(wingedYVelocityOption, ship);
		} else if (levelNum == LEVEL_2) {
			((GalagaLevel_2) gameLevel).moveWinged(wingedYVelocityOption, ship);
		} else if (levelNum == LEVEL_3) {
			((GalagaLevel_3) gameLevel).moveWinged(wingedYVelocityOption, ship);
		}
	}

	// calling default constructors of GalagaLevels
	@Override
	protected void setUpLevelToConstructorNoParameterMap() {
		levelToConstructorNoParameter = new HashMap<Integer, Supplier<GameLevel>>();
		levelToConstructorNoParameter.put(LEVEL_1, GalagaLevel_1::new);
		levelToConstructorNoParameter.put(LEVEL_2, GalagaLevel_2::new);
		levelToConstructorNoParameter.put(LEVEL_3, GalagaLevel_3::new);
	}
	
	// check if the player wins in the specific level of Breakout
	public boolean checkIsWinInEachLevel() {
//		return gameLevel.isWinnerInLevel;
		return gameLevel.getIsWinningAtEachLevel();
	}
	
}
