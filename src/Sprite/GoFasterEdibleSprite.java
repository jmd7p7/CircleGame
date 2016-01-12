package Sprite;

import java.awt.Color;
import java.util.Random;

public class GoFasterEdibleSprite extends EdibleSprite{
	private static final int SPEED_INCREASE_MAX = 6;
	
	public GoFasterEdibleSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, Color.LIGHT_GRAY, horizontalSpeed, verticalSpeed, startingY_coord);
	}

	@Override
	public int getSpeedIncreaseReward() {
		Random ran = new Random();
		return ran.nextInt(SPEED_INCREASE_MAX);
	}

	@Override
	public int getSizeIncreaseReward() {
		return 0;
	}

}
