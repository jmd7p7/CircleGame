package sprites;

import Positioning.Coordinate;
import UI.ICollisionHandler;
import paths.PathProvider;

public class SpeedupPlayerSprite extends GameSprite {

	private PathProvider pathProvider;

	public SpeedupPlayerSprite(Coordinate coord, int radius, int speed, PathProvider pathProvider) {
		super(coord, radius, speed, java.awt.Color.LIGHT_GRAY);

		this.pathProvider = pathProvider;
	}
	@Override
	public void updatePosition() {
		setCoordiante(this.pathProvider.getNextCoordinate(this.getX() + this.getSpeed()));
	}

	@Override
	public void handleCollisionWithPlayer(ICollisionHandler handler) {
		int radiusChange = 0;
		int speedChange = this.getRadius()/5;
		int points = speedChange > 0 ? speedChange : 1;
		handler.HandleChangePlayerSprite(radiusChange, speedChange);
		handler.HandleScoreUpdate(points);
		handler.HandleCollisionSound("Ally");
	}

}
