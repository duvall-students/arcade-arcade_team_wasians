package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCGalagaWingedYellow.java
 * Implement setting the related properties of yellow flying objects in the Galaga.
 * 
 */

public class NPCGalagaWingedYellow extends NPCGalaga {
	
	public NPCGalagaWingedYellow(int eachRowWingeds, int wingedsOffsetFromTop, String imageSource) {
		super(eachRowWingeds, wingedsOffsetFromTop);
		npcImageSrc = imageSource;
	}

}
