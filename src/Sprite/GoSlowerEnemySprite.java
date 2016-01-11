package Sprite;

import java.awt.Color;
import java.util.Random;

public class GoSlowerEnemySprite extends EnemySprite{
	
	private static final int MAX_SPEED_DECREASE = 6;
	
	public GoSlowerEnemySprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, java.awt.Color.ORANGE, horizontalSpeed, verticalSpeed, startingY_coord);
	}

	@Override
	public int getSizeDecreasePenalty() {
		return 0;
	}

	@Override
	public int getSpeedDecreasePenalty() {
		Random ran = new Random();
		return ran.nextInt(MAX_SPEED_DECREASE);
	}
}
