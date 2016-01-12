package Sprite;

import java.awt.Color;

public class GetBiggerEdibleSprite extends EdibleSprite{
	public GetBiggerEdibleSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, Color.blue, horizontalSpeed, verticalSpeed, startingY_coord);
	}

	@Override
	public int getSpeedIncreaseReward() {
		return 0;
	}

	@Override
	public int getSizeIncreaseReward() {
		return this.getRadius()/4;
	}

}
