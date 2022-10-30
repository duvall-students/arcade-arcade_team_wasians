package gameComponent.NPCObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Point2D;

import games.Game;

/**
 * @author Xu Yan
 * 
 * GameNPC.java
 * 
 */

public abstract class GameNPC {
	
	/**
	 * variables / properties about the NPC in the Breakout Game.
	 */
	
	/**
	 * variables / properties about the NPC (winged) in the Galaga Game.
	 */
	// velocity of winged
	protected Point2D wingedVelocity;
	
	/**
	 * variables / properties about the NPC in every Arcade Game.
	 */
	// game display
	private final int CANVAS_WIDTH = Game.SIZE;
	private final int CANVAS_HEIGHT = Game.SIZE;
	// empty spaces between NPCs 
	private final int NPC_SPACE_H = 6;
	private final int NPC_SPACE_V = 4;
	private int NPC_Y_OFFSET;
	// NPC image and display
	protected ImageView npc;
	protected String npcImageSrc;
	private int EACH_ROW_NPC;
	protected int GENERAL_NPC_HEIGHT;
	public int GENERAL_NPC_WIDTH;
	protected int NPC_IMAGE_HEIGHT;
	private int NPC_IMAGE_WIDTH;
	private final int GENERAL_NPC_SHRINK_SIZE = 0;
	
	protected GameNPC(int eachRowNpcs, int npcsOffsetFromTop) {
		EACH_ROW_NPC = eachRowNpcs;
		NPC_Y_OFFSET = npcsOffsetFromTop;
		GENERAL_NPC_WIDTH = (CANVAS_WIDTH - GENERAL_NPC_SHRINK_SIZE) / EACH_ROW_NPC - NPC_SPACE_H;
		NPC_IMAGE_WIDTH = GENERAL_NPC_WIDTH;
	}
	
	// set the overall NPC's properties
	public void setNPC(int col, int row) {
		try {
			// set the NPC's image
			Image npcImage = new Image(new FileInputStream(npcImageSrc));
			npc = new ImageView(npcImage);
		} catch(FileNotFoundException e) {
			System.out.println("The related NPC Image not found.");
		}
		// set the width and height of each NPC image
		npc.setFitWidth(NPC_IMAGE_WIDTH);
		npc.setFitHeight(NPC_IMAGE_HEIGHT);
		// set the (x, y) coordinate of each NPC
		npc.setX((NPC_SPACE_H / 2) + col * (GENERAL_NPC_WIDTH + NPC_SPACE_H));
		npc.setY((NPC_Y_OFFSET) + row * (GENERAL_NPC_HEIGHT + NPC_SPACE_V));
	}
	
	public Node getNPC() {
		return npc;
	}
	
	public ImageView getNPCImageView() {
		return npc;
	}
	
}
