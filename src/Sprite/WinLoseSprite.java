package Sprite;

import java.awt.Color;

public abstract class WinLoseSprite extends GameSprite{

	protected static final Boolean PLAYER_WINS = true;
	protected static final Boolean PLAYER_LOOSES = false;
	public WinLoseSprite(int radius, Color color, int horizontalSpeed, int verticalSpeed, int startingY_coord) {
		super(radius, color, horizontalSpeed, verticalSpeed, GameSpriteType.WINLOSE, startingY_coord);
	}
	public abstract Boolean getPlayerWinLoseStatus();
}
