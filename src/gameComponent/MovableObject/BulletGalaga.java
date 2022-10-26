package gameComponent.MovableObject;

import javafx.geometry.Point2D;

public class BulletGalaga extends MovableObject{
	public static String BALL_IMAGE = "resources/brick1.gif";

	public BulletGalaga(int screenSize, int startY) {
		super(BALL_IMAGE, screenSize, startY);
		// make sure it stays within the bounds
		myView.setFitWidth(3);
		myView.setFitHeight(10);
		
		myView.setX(screenSize/2.0);
		myView.setY(startY);
		myVelocity = new Point2D(0,100);
	}
	
	

}
