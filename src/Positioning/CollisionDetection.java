package Positioning;

import Utility.Utility;
import sprites.Sprite;

public class CollisionDetection {
	public static Boolean detectCollisionBetweenSprites(Sprite sprite1, Sprite sprite2) {
		// Preconditions: Sprite is circular shaped. The sprite's x and y
		// coordinates
		// define the top left corner of the rectangle that contains the
		// circular sprite.
		
		Double Sprite1_CenterPointXCoord = (double) sprite1.getX() + sprite1.getRadius();
		Double Sprite1_CenterPointYCoord = (double) sprite1.getY() + sprite1.getRadius();

		Double Sprite2_CenterPointXCoord = (double) sprite2.getX() + sprite2.getRadius();
		Double Sprite2_CenterPointYCoord = (double) sprite2.getY() + sprite2.getRadius();

		if (Utility.getDistance(Sprite1_CenterPointXCoord, Sprite1_CenterPointYCoord, Sprite2_CenterPointXCoord,
				Sprite2_CenterPointYCoord) < (sprite1.getRadius() + sprite2.getRadius())) {
			return true;
		}
		return false;
	}
}
