package Sprite;

import java.awt.Color;

public class LoseSprite extends WinLoseSprite{

	public LoseSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, java.awt.Color.BLACK, horizontalSpeed, verticalSpeed, startingY_coord);
	}

	@Override
	public Boolean PlayerWins() {
		return WinLoseSprite.PLAYER_LOOSES;
	}

}
