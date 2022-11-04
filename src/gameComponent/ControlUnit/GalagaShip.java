package gameComponent.ControlUnit;
import javafx.geometry.Point2D;

/**
 * @author Chris lee
 * extends ControlUnit, handles behaviors needed by GalagaShip. 
 */

public class GalagaShip extends ControlUnit {
	
	public static final int SHIP_WIDTH = 50;
	public static final int SHIP_HEIGHT = 50;
	public static final double PLATFORM_HALF_WIDTH = SHIP_WIDTH / 2.0;
	public static final double PLATFORM_X_LOC = 1.0/2;
	public static final double PLATFORM_Y_LOC = 8.0/10;
	
	public static final String SHIP_IMAGE = "resources/galagaShip.png";
	
	public GalagaShip(int canvasSize){
		super(SHIP_IMAGE, canvasSize, canvasSize * PLATFORM_X_LOC - PLATFORM_HALF_WIDTH, PLATFORM_Y_LOC * canvasSize, SHIP_WIDTH, SHIP_HEIGHT);
	}
	
	//returns the coordinate at which the bullet needs to be spawned.
	public Point2D getBulletSpawnLoc() {
		Point2D coordinate = new Point2D(this.getX() + (SHIP_WIDTH/2.0), this.getY());
		return coordinate;
	}

}
