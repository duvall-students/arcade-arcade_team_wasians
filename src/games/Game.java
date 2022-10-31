package games;

import gamePlaySystem.Player;

import java.util.HashMap;
import java.util.function.Supplier;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @Author: Hunter Copeland
 */

interface Game {

	public void start(Stage stage);

	public Scene setupGame(int size, Paint background);

	public void step(double elapsedTime);

	public void moveFrame(double elapsedTime);

	public void levelTransition();

	public void setUpLevelToConstructorNoParameterMap();

}
