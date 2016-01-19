package Sprite;

import java.awt.Color;

public class GetBiggerEdibleSprite extends EdibleSprite{
	public GetBiggerEdibleSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord, long gestationPeriod) {
		super(radius, Color.blue, horizontalSpeed, verticalSpeed, startingY_coord, gestationPeriod);
	}

	@Override
	public int getSpeedIncreaseReward() {
		return 0;
	}

	@Override
	public int getSizeIncreaseReward() {
		return this.getRadius()/4;
	}

	@Override
	public int getScoreIncreaseAmount() {
		return this.getRadius()/4;
	}

}
