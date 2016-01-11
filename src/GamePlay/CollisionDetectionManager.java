package GamePlay;

import Sprite.Sprite;
import Utility.Utility;

public class CollisionDetectionManager {
	//Preconditions: Sprite is circular shaped.  The sprite's x and y coordinates
	//				 define the top left corner of the rectangle that contains the 
	//				 circular sprite.
	public static Boolean detectCollisionBetweenTwoSprites(Sprite sprite1, Sprite sprite2){
		Double Sprite1_CenterPointXCoord = (double) sprite1.getX_Coord() + sprite1.getRadius();
		Double Sprite1_CenterPointYCoord = (double) sprite1.getY_Coord() + sprite1.getRadius();
		
		Double Sprite2_CenterPointXCoord = (double) sprite2.getX_Coord() + sprite2.getRadius();
		Double Sprite2_CenterPointYCoord = (double) sprite2.getY_Coord() + sprite2.getRadius();
		
		if(Utility.getDistance(Sprite1_CenterPointXCoord, Sprite1_CenterPointYCoord,
				Sprite2_CenterPointXCoord, Sprite2_CenterPointYCoord) < 
				(sprite1.getRadius() + sprite2.getRadius())){
			return true;
		}
		return false;
	}
	
	public static Boolean detectCollisionBetweenSpriteAndBottomOfPanel(Sprite sprite, int panelHeight){
		if(sprite.getY_Coord() + sprite.getRadius()*2 >= panelHeight)
			return true;	
		return false;
	}
	
	public static Boolean detectCollisionBetweenSpriteAndTopOfPanel(Sprite sprite){
		if(sprite.getY_Coord() <= 0)
			return true;	
		return false;
	}
	
	
}
