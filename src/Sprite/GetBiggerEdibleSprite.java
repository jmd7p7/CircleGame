package Sprite;

import java.awt.Color;
import java.util.Random;

public class GetBiggerEdibleSprite extends EdibleSprite{
	public GetBiggerEdibleSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, java.awt.Color.blue, horizontalSpeed, verticalSpeed, startingY_coord);
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
