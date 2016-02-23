package sprites;

import Positioning.Coordinate;
import UI.ICollisionHandler;
import paths.PathProvider;

public class WinSprite extends GameSprite {
	
	private PathProvider pathProvider;

	public WinSprite(Coordinate coord, int radius, int speed, PathProvider pathProvider) {
		super(coord, radius, speed, java.awt.Color.GREEN);

		this.pathProvider = pathProvider;
		this.setCoordiante(pathProvider.getNextCoordinate(-(this.getRadius())));
	}

	@Override
	public void updatePosition() {
		setCoordiante(pathProvider.getNextCoordinate(this.getX() + this.getSpeed()));
	}

	@Override
	public void handleCollisionWithPlayer(ICollisionHandler handler) {
		handler.HandleCollisionSound("Win");
		handler.HandleScoreUpdate(this.getRadius() * 2);
		handler.HandleWin();
	}

}
