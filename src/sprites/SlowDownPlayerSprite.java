package sprites;

import Positioning.Coordinate;
import UI.ICollisionHandler;
import paths.PathProvider;

public class SlowDownPlayerSprite extends GameSprite {

	private PathProvider pathProvider;

	public SlowDownPlayerSprite(Coordinate coord, int radius, int speed, PathProvider pathProvider) {
		super(coord, radius, speed, java.awt.Color.ORANGE);

		this.pathProvider = pathProvider;
	}

	@Override
	public void updatePosition() {
		setCoordiante(pathProvider.getNextCoordinate(this.getX() + this.getSpeed()));
	}

	@Override
	public void handleCollisionWithPlayer(ICollisionHandler handler) {
		int radiusChange = 0;
		int speedChange = -(this.getRadius()/5);
		int points = speedChange < 0 ? speedChange : -1;
		handler.HandleChangePlayerSprite(radiusChange, speedChange);
		handler.HandleScoreUpdate(points);
		handler.HandleCollisionSound("Enemy");
	}

}
