package Sprite;

import java.awt.Color;

public class LoseSprite extends WinLoseSprite{

	public LoseSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord, long gestationPeriod) {
		super(radius, Color.BLACK, horizontalSpeed, verticalSpeed, startingY_coord, gestationPeriod);
	}

	@Override
	public Boolean getPlayerWinStatus() {
		return WinLoseSprite.PLAYER_LOOSES;
	}

}
