package Sprite;

import java.awt.Color;

public abstract class EnemySprite extends GameSprite{

	public EnemySprite(int radius, Color color, int horizontalSpeed, int verticalSpeed,int startingY_coord) {
		super(radius, color, horizontalSpeed, verticalSpeed, GameSpriteType.ENEMY, startingY_coord);
	}
	
	public abstract int getSizeDecreasePenalty();
	public abstract int getSpeedDecreasePenalty();

}
