package Sprite;

import java.awt.Color;

public class GetSmallerEnemySprite extends EnemySprite{
	public GetSmallerEnemySprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, Color.RED, horizontalSpeed, verticalSpeed, startingY_coord);
	}

	@Override
	public int getSizeDecreasePenalty() {
		return this.getRadius()/4;
	}

	@Override
	public int getSpeedDecreasePenalty() {
		return 0;
	}

}
