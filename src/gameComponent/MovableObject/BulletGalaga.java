package gameComponent.MovableObject;

import javafx.geometry.Point2D;

public class BulletGalaga extends MovableObject{
	public static String BULLET_IMAGE = "resources/brick1.gif";
	public static int BULLET_HEIGHT = 10;
	public static int BULLET_WIDTH = 3;

	public BulletGalaga(int screenSize, int startY) {
		super(BULLET_IMAGE, screenSize, startY);
		// make sure it stays within the bounds
		myView.setFitWidth(BULLET_WIDTH);
		myView.setFitHeight(BULLET_HEIGHT);
		
		myView.setX(screenSize/2.0);
		myView.setY(startY);
		myVelocity = new Point2D(0,100);
	}
	
	

}
