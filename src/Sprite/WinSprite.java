package Sprite;

import java.awt.Color;

public class WinSprite extends WinLoseSprite{

	public WinSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, Color.green, horizontalSpeed, verticalSpeed, startingY_coord);
	}

	@Override
	public Boolean getPlayerWinLoseStatus() {
		return WinLoseSprite.PLAYER_WINS;
	}

}
