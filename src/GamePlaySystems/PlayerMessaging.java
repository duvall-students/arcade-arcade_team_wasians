import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;

//private Scene myScene;

//try setting up text in launchgame class

//variables
//public static final Text score = new Text("cf");
//public static final Text health = new Text("dtg");
//private Text tEat = new Text("sad");
//public static final Text endMessage = new Text("cf");
//public static final Text powerUpMessage = new Text("dtg");
//declare text node, use this.text, change the text in the gmae launch method

public class GameScreenMessage {
	private Text endMessage = new Text();
	private Text scoreMessage = new Text();
	private Text healthMessage = new Text();
	private Text levelMessage = new Text();
	ImageView text = new ImageView();
	// create method that runs during game time loop and updates text
	
//	public GameScreenMessage(Player player) {
//		
//	}

	public GameScreenMessage() {
		// TODO Auto-generated constructor stub
	}


	//	 display current score
	public Text displayScore(Player player) {
		scoreMessage.setText("Your score: " + player.getScore());
		scoreMessage.setX(50);
		scoreMessage.setY(25);
		return scoreMessage;
	}

	// display health
	public void displayHealth(Player player) {
		healthMessage.setText("Health Left: " + player.getHealth());
		healthMessage.setX(100);
		healthMessage.setY(25);
	}
	
	// display level number
	public void displayLevelNum(Canvas canvas) {
		levelMessage.setText("Level " );//+ Canvas.getLevelNum());
		levelMessage.setX(150);
		levelMessage.setY(25);
	}
	
	// display you win
	public void displayWinningMessage() {
		endMessage.setText("You win!!!");
		endMessage.setX(150);
		endMessage.setY(25);
	}
	
	public void displayLosingMessage() {
		endMessage.setText("Game Over");
		endMessage.setX(150);
		endMessage.setY(25);
	}

	public Node getView() {
		// TODO Auto-generated method stub
		return text;
	}
	// image build impacts
//	public Node getView () {
//		return Ball.class.;
//	}
	
//public void main (Stage stage) throws FileNotFoundException {
//
//
//    //Creating a text object
//    //private Text text = new Text();
//    //Setting the properties of text
//    text.setText("A");
//    text.setWrappingWidth(580);
//    text.setX(10.0);
//    text.setY(25.0);
//    //Setting the stage
//    Group root = new Group(text);
//    Scene scene = new Scene(root, 595, 300, Color.BEIGE);
//    stage.setTitle("Displaying Text");
//    stage.setScene(scene);
//    stage.show();
// }
//}
	
/*	public GameScreenMessage(Player player) {
		
	}

//	 display current score
	public void displayScore(Player player) {
		Text score = new Text("Your score: " + player.getScore());
		score.setX(50);
		score.setY(25);
	}

	// display health
	public void displayHealth(Player player) {
		Text health = new Text("Health Left: " + player.getHealth());
		health.setX(100);
		health.setY(25);
	}
	
	// display level number
	public void displayLevelNum(Canvas canvas) {
		Text levelNum = new Text("Level " + Canvas.getLevelNum());
		levelNum.setX(150);
		levelNum.setY(25);
	}
	
	// display you win
	public void displayWinningMessage() {
		Text endMessage = new Text("You win!!!");
		endMessage.setX(150);
		endMessage.setY(25);
	}
	
	public void displayLosingMessage() {
		Text endMessage = new Text("Game Over");
		endMessage.setX(150);
		endMessage.setY(25);
	}
	*/
}
