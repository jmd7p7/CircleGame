package Sprite;

import java.awt.Color;

public class WinSprite extends WinLoseSprite{

	public WinSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord, long gestationPeriod) {
		super(radius, Color.green, horizontalSpeed, verticalSpeed, startingY_coord, gestationPeriod);
	}

	@Override
	public Boolean getPlayerWinStatus() {
		return WinLoseSprite.PLAYER_WINS;
	}

	public int getScoreIncreaseAmount(){
		return this.getRadius();
	}
}
