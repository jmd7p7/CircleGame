package Sprite;

import java.awt.Color;

public abstract class EdibleSprite extends GameSprite{
	public EdibleSprite(int radius, Color color, int horizontalSpeed, int verticalSpeed, int startingY_coord, long gestationPeriod) {
		super(radius, color, horizontalSpeed, verticalSpeed, GameSpriteType.EDIBLE, startingY_coord, gestationPeriod);
	}
	
	public abstract int getSpeedIncreaseReward();
	public abstract int getSizeIncreaseReward();
	public abstract int getScoreIncreaseAmount();
}
