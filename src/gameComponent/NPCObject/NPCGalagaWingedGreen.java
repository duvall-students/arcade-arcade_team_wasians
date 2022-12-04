package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCGalagaWingedGreen.java
 * Implement setting the related properties of green flying objects in the Galaga.
 * 
 */

public class NPCGalagaWingedGreen extends NPCGalaga {

	public NPCGalagaWingedGreen(int eachRowWingeds, int wingedsOffsetFromTop, String imageSource) {
		super(eachRowWingeds, wingedsOffsetFromTop);
		npcImageSrc = imageSource;
	}
	
}
