package Sprite;

import java.awt.Color;
import Positioning.Coordinate;

import GamePlay.DirectionChangeType;
import GamePlay.GameSpriteDirectionRandomizer;

public abstract class GameSprite extends Sprite {
	private final int horizontalSpeed;
	private final int verticalSpeed;
	private final GameSpriteType type;
	private GameSpriteDirectionRandomizer directionRandomizer;
	private long gestationPeriodInMillis;
	private long timeAtConceptionInMillis;

	public GameSprite(int radius, Color color, int horizontalSpeed, int verticalSpeed, GameSpriteType type,
			int startingY_coord, long gestationPeriod) {
		super(radius, color, startingY_coord);
		this.verticalSpeed = verticalSpeed;
		this.horizontalSpeed = horizontalSpeed;
		this.type = type;
		this.directionRandomizer = new GameSpriteDirectionRandomizer();
		this.gestationPeriodInMillis = gestationPeriod;
		this.timeAtConceptionInMillis = System.currentTimeMillis();
	}

	public void updatePosition(Double delta) {
		if (System.currentTimeMillis() - this.timeAtConceptionInMillis > this.gestationPeriodInMillis) {
			int newX_coord = this.getX_Coord() + (int) (delta * horizontalSpeed);
			int newY_coord;
			switch (directionRandomizer.getDirectionChange()) {
			case UP:
				newY_coord = this.getY_Coord() - verticalSpeed;
				break;
			case DOWN:
				newY_coord = this.getY_Coord() + verticalSpeed;
				break;
			default:
				newY_coord = this.getY_Coord();
			}
			this.setCoordinate(new Coordinate(newX_coord, newY_coord));
		}
	}

	public GameSpriteType getGameSpriteType() {
		return this.type;
	}

	public void changeDirectoin() {
		this.directionRandomizer.forceDirectionChange();
	}
}
