package gamePlaySystem.LevelSystem;

import java.util.HashMap;
import java.util.function.Supplier;

import javafx.scene.Group;
import javafx.stage.Stage;

import gamePlaySystem.Player;
import gameComponent.MovableObject.BallBreakout;

/**
 * @author Xu Yan
 * 
 * GameLevelControl.java
 * 
 */

public abstract class GameLevelControl {
	
	// properties and variables associated with the Level
	private final int TOTAL_LEVELS = 3;
	protected int levelNum;
	public int getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}

	protected int levelUpNum;
	protected HashMap<Integer, Supplier<GameLevel>> levelToConstructorNoParameter;
	protected GameLevel gameLevel;
	
	public GameLevelControl(Group root, int levelNum) {
		this.levelNum = levelNum;
		setUpLevelToConstructorNoParameterMap();
		generateLevel(root);
	}
	
	// calling default constructors of BreakoutLevels
	protected abstract void setUpLevelToConstructorNoParameterMap();
	
	// deal with the level transition when the player wins
	protected abstract void levelTransition(Stage myStage, Player player);
	
	// deal with the collision of the movable and immovable elements
//	public abstract void getElementsCollisionInEachLevel(Stage myStage, Group root, BallBreakout ball, Player player);
	
	// set up the overall properties of the level
	private void generateLevel(Group root) {
		Supplier<GameLevel> supplier = levelToConstructorNoParameter.get(levelNum);
		gameLevel = supplier.get();
		gameLevel.createNPCs(root);
	}
	
	// get the player allowed health in specific level
	public int getPlayerAllowedHealth() {
		return gameLevel.getAllowedHealth();
	}
	
}
