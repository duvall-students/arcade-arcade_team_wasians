package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCGalagaWingedPowerUp.java
 * Implement setting the related properties of power-up NPCs in the Galaga.
 * 
 */

public class NPCGalagaWingedPowerUp extends NPCGalaga {
	
	public NPCGalagaWingedPowerUp(int eachRowWingeds, int wingedsOffsetFromTop, String imageSource) {
		super(eachRowWingeds, wingedsOffsetFromTop);
		npcImageSrc = imageSource;
	}
	
}
