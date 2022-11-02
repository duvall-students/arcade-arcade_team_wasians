package gameComponent.MovableObject;

import gamePlaySystem.Player;
import gameComponent.ControlUnit.BreakOutPaddle;
import gamePlaySystem.LevelSystem.BreakoutLevelControl;
import gamePlaySystem.LevelSystem.BreakoutLevels;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BallBreakout extends MovableObject{
	
	public static String BALL_IMAGE = "resources/ball.gif";
	public static final int BALL_SIZE = 20;
	public static final int VELOCITY_NUM_RANGE = 5; 
	public static final double POWER_UP_SPEED_MULTIPLIER = 1.2;
	public static final int X_START_VELOCITY = 25;
	public static final int Y_START_VELOCITY = 100;
	
	public BallBreakout(int screenSize, int startY) {
		super(BALL_IMAGE, screenSize, startY, BALL_SIZE, BALL_SIZE);
		myVelocity = new Point2D(X_START_VELOCITY, Y_START_VELOCITY);
	}
	
	
	//changes the velocity of the ball. 
	public void bounceOnWalls (double size) {
		// collides with Left Wall
		if (myView.getX() < 0 ) {
			reverseX(false);
			myView.setX(0);
			}
		// collides with the right wall
		else if (myView.getX() > getRightBoundary(size)) {
			reverseX(false);
			myView.setX(getRightBoundary(size));
		}
		
		//collides with the top wall
		if (myView.getY() < 0) {
			reverseY(false);
		}
	}
	
	public void bounceOnRectangle(ImageView rectangle, boolean isPaddle){
		if (isTopBtmHit(rectangle)) {
			reverseY(isPaddle);
		}
		
		if (isSideHit(rectangle)){
			reverseX(isPaddle);
			//pushOut(rectangle);
		}
		
		if (isCornerHit(rectangle)) {
			reverseX(isPaddle);
			reverseY(isPaddle);
		}
	}
		
	private void reverseY(boolean isPaddle) {
		if (isPaddle) {
			System.out.println("Paddle Collide");
			setVelocity(addRandomToXVelocity(),  -Math.abs(myVelocity.getY()));
		}
		else {
			setVelocity(addRandomToXVelocity(),  -myVelocity.getY() + getRandomInRange(-5, 5));
		}
	}

	//reverse the XVelocity
	private void reverseX(boolean isPaddle) {
		//case 1: collides with paddle's left side.
		if (isPaddle && myVelocity.getX() > 0) {
			setVelocity(-Math.abs(myVelocity.getX()), addRandomToYVelocity());
		}
		//case 2: collides with paddle's right side.
		else if (isPaddle && myVelocity.getX() < 0) {
			setVelocity(Math.abs(myVelocity.getX()), addRandomToYVelocity());
		}
		//case 3: collides with Bricks.
		else {
			setVelocity(-myVelocity.getX() + getRandomInRange(-5,5), addRandomToYVelocity());
		}
	}

//	//returns true if the ball hit left side of a Game Object.
//	private boolean isLeftHit(ImageView rectangle) {
//		return getCenterX() < rectangle.getX() && getCenterY() > rectangle.getY();
//	}

	//returns true if the ball hit the side of a Game Object.
	private boolean isSideHit(ImageView rectangle){
		return (getCenterX() > rectangle.getX() + rectangle.getFitWidth() && getCenterY() > rectangle.getY()) ||
				(getCenterX() < rectangle.getX() && getCenterY() > rectangle.getY());
	}

	//returns true if the ball hit the top or bottom of a Game Object.
	private boolean isTopBtmHit(ImageView rectangle) {
		return (getCenterX() > rectangle.getX() && getCenterX() < rectangle.getX() + rectangle.getFitWidth());
	}
	
	//returns true if the ball hit the corners of a Game Object.
	private boolean isCornerHit(ImageView rectangle) {
		return (getCenterX() < rectangle.getX() && getCenterY() < rectangle.getY()) || //Left Upper Corner
				(getCenterX() > rectangle.getX() + rectangle.getFitWidth() && getCenterY() < rectangle.getY()) || //Right Upper Corner
				(getCenterX() < rectangle.getX() && getCenterY() > rectangle.getY() + rectangle.getFitHeight()) || //Left Lower Corner
				(getCenterX() > rectangle.getX() + rectangle.getFitWidth() && getCenterY() > rectangle.getY() + rectangle.getFitHeight()); //Right Lower Corner
	}


	public boolean isBallOut(int sceneSize) {
		return myView.getY() >  sceneSize - myView.getBoundsInLocal().getHeight();
	}
//	//pushes the ball outside of ImageView platform (to the left)
//	private void pushOutLeft(ImageView rectangle) {
//		myView.setX(rectangle.getX() - myView.getFitWidth());
//	}
//	
	//introduces some randomness to the Y Velocity. 
	private double addRandomToYVelocity() {
		return myVelocity.getY() + getRandomInRange(-VELOCITY_NUM_RANGE, VELOCITY_NUM_RANGE);
	}
	
	//introduces some randomness to the X Velocity. 
	private double addRandomToXVelocity() {
		return myVelocity.getX() + getRandomInRange(-VELOCITY_NUM_RANGE, VELOCITY_NUM_RANGE);
	}
	
	public void resetBall(int screenSize, int startY) {
		myView.setX(screenSize/2.0);
		myView.setY(startY);
	}
	
	//power up feature makes the ball go faster!
	public void powerUpBall() {
		setVelocity(POWER_UP_SPEED_MULTIPLIER * myVelocity.getX(), POWER_UP_SPEED_MULTIPLIER * myVelocity.getY());
	}
	
	public void bounceOnPlatform(BreakOutPaddle platform) {
		if (isHitObject(platform.getShape())) {
			bounceOnRectangle(platform.getShape(), true);
		}
	}
	
	//if the ball is out of bounds, reset the ball and lose health. 
	public void handleOutofBounds(int SIZE, BreakoutLevelControl level, Player player) {
		if (isBallOut(SIZE)) {
			resetBall(SIZE, (int)(SIZE * level.getBallStartingPosition()));
			player.setReadytoPlay(false);
			player.playerLoseHealth(player);
		}
	}
	
	private double getRightBoundary(double size) {
		return (size - myView.getBoundsInLocal().getWidth());
	
	}
	
	
	//handles every step of ball movement in a given frame. 
	public void handleBallMovement(double elapsedTime, double size, BreakOutPaddle platform, BreakoutLevelControl level, Player player) {
		move(elapsedTime);
		bounceOnWalls(size);
		bounceOnPlatform(platform);
		handleOutofBounds((int)size, level, player);
	}

	
	
	
	
	
}
