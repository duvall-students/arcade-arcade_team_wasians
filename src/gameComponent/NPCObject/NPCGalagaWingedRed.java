package gameComponent.NPCObject;

/**
 * @author Xu Yan
 * 
 * NPCGalagaWingedRed.java
 * Implement setting the related properties of red flying objects in the Galaga.
 * 
 */

public class NPCGalagaWingedRed extends NPCGalaga {
	
	public NPCGalagaWingedRed(int eachRowWingeds, int wingedsOffsetFromTop, String imageSource) {
		super(eachRowWingeds, wingedsOffsetFromTop);
		npcImageSrc = imageSource;
	}

}
