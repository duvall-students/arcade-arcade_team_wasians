package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCBreakout.java
 * Implement setting the related properties of different types of bricks in the Breakout.
 * 
 */

public abstract class NPCBreakout extends GameNPC{

	protected NPCBreakout(int eachRowNpcs, int npcsOffsetFromTop) {
		super(eachRowNpcs, npcsOffsetFromTop);
		final int GENERAL_BRICK_HEIGHT = 8;
		GENERAL_NPC_HEIGHT = GENERAL_BRICK_HEIGHT;
		NPC_IMAGE_HEIGHT = GENERAL_NPC_HEIGHT;
	}

}
