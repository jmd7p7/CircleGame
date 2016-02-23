package sprites;

import Positioning.Coordinate;
import UI.ICollisionHandler;
import paths.PathProvider;

public class ShrinkPlayerSprite extends GameSprite{
	
	private PathProvider pathProvider;
	
	public ShrinkPlayerSprite(Coordinate coord, int radius, int speed, PathProvider pathProvider){
		super(coord, radius, speed, java.awt.Color.RED);
		
		this.pathProvider = pathProvider;
		this.setCoordiante(pathProvider.getNextCoordinate(-(this.getRadius())));
	}

	@Override
	public void updatePosition() {
		setCoordiante(pathProvider.getNextCoordinate(this.getX() + this.getSpeed()));
	}

	@Override
	public void handleCollisionWithPlayer(ICollisionHandler handler) {
		int radiusChange = -(this.getRadius()/4);
		int speedChange = 0;
		int points = radiusChange < 0 ? radiusChange : -1;
		handler.HandleChangePlayerSprite(radiusChange, speedChange);
		handler.HandleCollisionSound("Enemy");
		handler.HandleScoreUpdate(points);
	}

}
