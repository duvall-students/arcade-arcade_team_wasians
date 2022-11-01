package gamePlaySystem.LevelSystem;

import java.util.HashMap;
import java.util.function.Supplier;

import gameComponent.MovableObject.BallBreakout;
import javafx.scene.Group;
import javafx.stage.Stage;

import gamePlaySystem.Player;


public abstract class GameLevelControl {
	
	// properties and variables associated with the Level
	private final int TOTAL_LEVELS = 3;
	protected int levelNum;
	protected int levelUpNum;
	protected HashMap<Integer, Supplier<GameLevel>> levelToConstructorNoParameter;
	protected GameLevel gameLevel;
	
	public GameLevelControl(Group root, int levelNum) {
		this.levelNum = levelNum;
		setUpLevelToConstructorNoParameterMap();
		generateLevel(root);
	}
	
	protected abstract void setUpLevelToConstructorNoParameterMap();
	
	protected abstract void levelTransition(Stage myStage, Player player);
	
	public abstract void getElementsCollisionInEachLevel(Stage myStage, Group root, BallBreakout ball, Player player);
	
	private void generateLevel(Group root) {
		Supplier<GameLevel> supplier = levelToConstructorNoParameter.get(levelNum);
		gameLevel = supplier.get();
		gameLevel.createNPCs(root);
	}
	
	public int getPlayerAllowedHealth() {
		return gameLevel.getAllowedHealth();
	}
	
}
