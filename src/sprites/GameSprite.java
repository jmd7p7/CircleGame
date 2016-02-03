package sprites;

import Positioning.Coordinate;
import UI.ICollisionHandler;

public abstract class GameSprite extends Sprite {
	
	public GameSprite(Coordinate coord, int radius, int speed, java.awt.Color color){
		super(coord, radius, speed, color);
	}
	
	public abstract void updatePosition();
	public abstract void handleCollisionWithPlayer(ICollisionHandler handler);
}
