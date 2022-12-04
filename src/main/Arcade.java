package main;

import java.util.Scanner;

import games.Breakout;
import games.Galaga;
import games.Game;

/**
 * @Author: Hunter Copeland
 * Takes input from the console to determine which game will be played
 */

public class Arcade {

	Scanner scanner = new Scanner(System.in);
	
	public void runGames() {
		System.out.println("Hello! Which game would you like to play?");
		System.out.println("1.    Breakout(B)");
		System.out.println("2.    Galaga(G)");
		
		String gameAnswer = scanner.nextLine();
		gameAnswer.toUpperCase();
		
		if(gameAnswer.contentEquals("B")) {
			Breakout breakout = new Breakout();
			breakout.runBreakout();
		} else {
			Galaga galaga = new Galaga();
			galaga.runGalaga();
		}
		
	}
	
}
