package Sprite;

import java.awt.Color;
import java.util.Random;

public class GoFasterEdibleSprite extends EdibleSprite{

	public GoFasterEdibleSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord, long gestationPeriod) {
		super(radius, Color.MAGENTA, horizontalSpeed, verticalSpeed, startingY_coord, gestationPeriod);
	}

	@Override
	public int getSpeedIncreaseReward() {
		return this.getRadius()/5;
	}

	@Override
	public int getSizeIncreaseReward() {
		return 0;
	}

	@Override
	public int getScoreIncreaseAmount() {
		return this.getRadius()/5;
	}

}
