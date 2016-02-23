package sprites;

import Positioning.Coordinate;
import UI.ICollisionHandler;
import paths.PathProvider;

public class EnlargePlayerSprite extends GameSprite{

	private PathProvider pathProvider;

	public EnlargePlayerSprite(Coordinate coord, int radius, int speed, PathProvider pathProvider) {
		super(coord, radius, speed, java.awt.Color.BLUE);

		this.pathProvider = pathProvider;
		this.setCoordiante(pathProvider.getNextCoordinate(-(this.getRadius())));
	}
	@Override
	public void updatePosition() {
		setCoordiante(this.pathProvider.getNextCoordinate(this.getX() + this.getSpeed()));
	}

	@Override
	public void handleCollisionWithPlayer(ICollisionHandler handler) {
		int speedChange = 0;
		int radiusChange = this.getRadius()/4;
		int points = radiusChange > 0 ? radiusChange : 1;
		handler.HandleChangePlayerSprite(radiusChange, speedChange);
		handler.HandleCollisionSound("Ally");
		handler.HandleScoreUpdate(points);
	}


}
