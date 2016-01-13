package Sprite;

import java.awt.Color;
import java.util.Random;

public class GoSlowerEnemySprite extends EnemySprite{
	
	public GoSlowerEnemySprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, Color.ORANGE, horizontalSpeed, verticalSpeed, startingY_coord);
	}

	@Override
	public int getSizeDecreasePenalty() {
		return 0;
	}

	@Override
	public int getSpeedDecreasePenalty() {
		return this.getRadius()/5;
	}
}
