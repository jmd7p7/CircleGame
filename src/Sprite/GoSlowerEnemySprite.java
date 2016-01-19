package Sprite;

import java.awt.Color;
import java.util.Random;

public class GoSlowerEnemySprite extends EnemySprite{
	
	public GoSlowerEnemySprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord, long gestationPeriod) {
		super(radius, Color.ORANGE, horizontalSpeed, verticalSpeed, startingY_coord, gestationPeriod);
	}

	@Override
	public int getSizeDecreasePenalty() {
		return 0;
	}

	@Override
	public int getSpeedDecreasePenalty() {
		return this.getRadius()/5;
	}

	@Override
	public int getScoreReductionAmount() {
		return this.getRadius()/5;
	}
}
