package gameComponent.MovableObject;

import javafx.geometry.Point2D;
import gameComponent.ControlUnit.GalagaShip;

public class BulletGalaga extends MovableObject{
	public static String BULLET_IMAGE = "resources/brick1.gif";
	public static int BULLET_HEIGHT = 10;
	public static int BULLET_WIDTH = 3;

	public BulletGalaga(int screenSize, int startY, GalagaShip ship) {
		super(BULLET_IMAGE, screenSize, startY, BULLET_WIDTH, BULLET_HEIGHT);
		myVelocity = new Point2D(0,100);
		myView.setX(ship.getBulletSpawnLoc().getX());
		myView.setY(ship.getBulletSpawnLoc().getY());
	}
	
	

}
