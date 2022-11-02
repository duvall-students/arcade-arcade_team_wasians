package gamePlaySystem.LevelSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import javafx.scene.Group;
import gameComponent.ControlUnit.GalagaShip;
import gameComponent.MovableObject.BallBreakout;
import gameComponent.NPCObject.GameNPC;
import gameComponent.NPCObject.NPCGalaga;
import gamePlaySystem.Player;

/**
 * @author Xu Yan
 * 
 * GalagaLevels.java
 * 
 */

public abstract class GalagaLevels extends GameLevel {

	// variables / properties about the level in Galaga Game
	protected final String GREEN = "green";
	protected final String RED = "red";
	protected final String YELLOW = "yellow";
	private List<GameNPC> greenWinged;
    protected List<GameNPC> redWinged;
    protected List<GameNPC> yellowWinged;
    protected List<GameNPC> powerUpWinged;
    private HashMap<Integer, String> powerUpWingedSettingsInLevels;
    protected HashMap<String, List<GameNPC>> wingedsListOfEachWingedType;
    protected HashMap<Integer, String> integerToStringOfEachWingedType;
    private final int EACH_WINGED_POINT = 1;
    private HashMap<String, Supplier<GameNPC>> wingedToConstructorNoParameter;
	
	protected GalagaLevels(int wingedsYOffsetInLevel, int levelNum) {
		super(wingedsYOffsetInLevel, levelNum);
		TOTAL_LEVELS = 3;
		// initialize the setup of collections
		initializeWingedsListOfEachWingedType();
		setWingedsListOfEachWingedType();
		setPowerUpWingedSettingsInLevels();
		setIntegerToStringOfEachWingedType();
	}
	
	// create the winged based on its type
	@Override
	public void createNPCs(Group root) {
		for (int row = 0; row < gameNPCLayout.length; row++) {
			for (int col = 0; col < gameNPCLayout[row].length; col++) {
				String typeStr = integerToStringOfEachWingedType.get(gameNPCLayout[row][col]);
				if (!typeStr.equals(EMPTY)) {
					generateWingeds(col, row, root, typeStr);
				}
			}
		}
	}
	
	// load the winged on the screen
	protected abstract void generateWingeds(int col, int row, Group root, String wingedType);
	
	protected abstract void moveWinged(double wingedYVelocityOption, GalagaShip ship);
	
	private void initializeWingedsListOfEachWingedType() {
		redWinged = new ArrayList<>();
		greenWinged = new ArrayList<>();
		yellowWinged = new ArrayList<>();
		powerUpWinged = new ArrayList<>();
	}
	
	// associate the level with its designated power-up in Galaga Game
	private void setPowerUpWingedSettingsInLevels() {
		// variables about the level
		final int LEVEL_1 = 1;
		final int LEVEL_2 = 2;
		final int LEVEL_3 = 3;
		powerUpWingedSettingsInLevels = new HashMap<Integer, String>();
		powerUpWingedSettingsInLevels.put(LEVEL_1, "scorepower");
		powerUpWingedSettingsInLevels.put(LEVEL_2, "scorepower");
		powerUpWingedSettingsInLevels.put(LEVEL_3, "scorepower");
	}
	
	// associate the winged type with its List of GameNPC in Galaga Game
	private void setWingedsListOfEachWingedType() {
		wingedsListOfEachWingedType = new HashMap<String, List<GameNPC>>();
		wingedsListOfEachWingedType.put(RED, redWinged);
		wingedsListOfEachWingedType.put(GREEN, greenWinged);
		wingedsListOfEachWingedType.put(YELLOW, yellowWinged);
		wingedsListOfEachWingedType.put(POWER_UP, powerUpWinged);
	}
	
	// associate the integer label with string label of each winged type in Galaga Game
	private void setIntegerToStringOfEachWingedType() {
		integerToStringOfEachWingedType = new HashMap<Integer, String>();
		integerToStringOfEachWingedType.put(0, EMPTY);
		integerToStringOfEachWingedType.put(1, POWER_UP);
		integerToStringOfEachWingedType.put(2, GREEN);
		integerToStringOfEachWingedType.put(3, RED);
		integerToStringOfEachWingedType.put(4, YELLOW);
	}

}
