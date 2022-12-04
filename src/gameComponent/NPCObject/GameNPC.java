package gameComponent.NPCObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import games.Game;

/**
 * @author Xu Yan
 * 
 * GameNPC.java
 * Implement setting the related properties of the GameNPC in different Arcade Games.
 * 
 */

public abstract class GameNPC {
	
	// variables / properties about the NPC in every Arcade Game.
	// empty spaces between NPCs 
	private final int NPC_SPACE_H = 6;
	private final int NPC_SPACE_V = 4;
	private int NPC_Y_OFFSET;
	// NPC image
	protected ImageView npc;
	protected String npcImageSrc;
	// NPC display
	private int EACH_ROW_NPC;
	protected int GENERAL_NPC_HEIGHT;
	public int GENERAL_NPC_WIDTH;
	protected int NPC_IMAGE_HEIGHT;
	private int NPC_IMAGE_WIDTH;
	private final int GENERAL_NPC_SHRINK_SIZE = 0;
	
	protected GameNPC(int eachRowNpcs, int npcsOffsetFromTop) {
		EACH_ROW_NPC = eachRowNpcs;
		NPC_Y_OFFSET = npcsOffsetFromTop;
		GENERAL_NPC_WIDTH = (Game.SIZE + GENERAL_NPC_SHRINK_SIZE) / EACH_ROW_NPC - NPC_SPACE_H;
		NPC_IMAGE_WIDTH = GENERAL_NPC_WIDTH;
	}
	
	// set the overall NPC's properties
	public void setNPC(int col, int row) {
		// set the NPC's image
		try {
			Image npcImage = new Image(new FileInputStream(npcImageSrc));
			npc = new ImageView(npcImage);
		} catch(FileNotFoundException e) {
			System.out.println("The related NPC image not found.");
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
