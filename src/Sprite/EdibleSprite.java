package Sprite;

import java.awt.Color;

public abstract class EdibleSprite extends GameSprite{
	public EdibleSprite(int radius, Color color, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, color, horizontalSpeed, verticalSpeed, GameSpriteType.EDIBLE, startingY_coord);
		// TODO Auto-generated constructor stub
	}
	
	public abstract int getSpeedIncreaseReward();
	public abstract int getSizeIncreaseReward();
}
