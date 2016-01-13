package Sprite;

import java.awt.Color;
import java.util.Random;

public class GoFasterEdibleSprite extends EdibleSprite{

	public GoFasterEdibleSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, Color.LIGHT_GRAY, horizontalSpeed, verticalSpeed, startingY_coord);
	}

	@Override
	public int getSpeedIncreaseReward() {
		return this.getRadius()/5;
	}

	@Override
	public int getSizeIncreaseReward() {
		return 0;
	}

}
