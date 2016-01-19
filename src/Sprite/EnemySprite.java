package Sprite;

import java.awt.Color;

public abstract class EnemySprite extends GameSprite{

	public EnemySprite(int radius, Color color, int horizontalSpeed, int verticalSpeed,int startingY_coord, long gestationPeriod) {
		super(radius, color, horizontalSpeed, verticalSpeed, GameSpriteType.ENEMY, startingY_coord, gestationPeriod);
	}
	
	public abstract int getSizeDecreasePenalty();
	public abstract int getSpeedDecreasePenalty();
	public abstract int getScoreReductionAmount();

}
