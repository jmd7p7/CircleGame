package Sprite;

import java.awt.Color;

public class WinSprite extends WinLoseSprite{

	public WinSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, java.awt.Color.green, horizontalSpeed, verticalSpeed, startingY_coord);
	}

	@Override
	public Boolean PlayerWins() {
		return WinLoseSprite.PLAYER_WINS;
	}

}
