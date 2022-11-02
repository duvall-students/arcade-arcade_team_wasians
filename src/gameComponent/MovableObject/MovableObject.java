package gameComponent.MovableObject;
/**
 * @author chris lee
 * handles ball behaviors such as, moving across the screen & bouncing
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//k

public abstract class MovableObject {

	
	public static final int BOUNCER_MIN_SPEED = -300;
	public static final int BOUNCER_MAX_SPEED = 300;
	public static final int BOUNCER_MIN_SIZE = 20;
	public static final int BOUNCER_MAX_SIZE = 20;
	protected Random dice = new Random();
	protected ImageView myView;
	protected Point2D myVelocity;

	/**
	 * Create a ball from a given image with random attributes.
	 */
	public MovableObject (String image, int screenWidth, int startY, int width, int height) {
		try {
			myView = new ImageView(new Image(new FileInputStream(image)));
		}
		catch (FileNotFoundException e) {
			System.out.println("Ball Image not found.");
		}
		
		// make sure it stays a circle
		myView.setFitWidth(width);
		myView.setFitHeight(height);
		
		myView.setX(screenWidth/2.0);
		myView.setY(startY);
	}

	//returns the image of the Ball.
	public Node getView () {
		return myView;
	}


	// Returns an "interesting", non-zero random value in the range (min, max)
	protected int getRandomInRange (int min, int max) {
		return min + dice.nextInt(max - min) + 1;
	}

	/**
	 * Move by taking one step based on its velocity.
	 *
	 * Note, elapsedTime is used to ensure consistent speed across different machines.
	 */
	public void move (double elapsedTime) {
		myView.setX(myView.getX() + myVelocity.getX() * elapsedTime);
		myView.setY(myView.getY() + myVelocity.getY() * elapsedTime);
	}
	
	//returns true if the ball hits the image passed. 
	public boolean isHitObject(ImageView image) {
		return myView.getBoundsInParent().intersects(image.getBoundsInParent());
	}
	
	//returns the Center X coordinate of the ball. 
	public double getCenterX() {
		return myView.getX() + myView.getFitWidth()  / 2.0;
	}
	
	//returns the Center Y coordinate of the ball. 
	public double getCenterY() {
		return myView.getY() + myView.getFitHeight()  / 2.0;
	}
	
	
	protected void setVelocity(double xVal, double yVal) {
		myVelocity = new Point2D(xVal, yVal);
	}

}


