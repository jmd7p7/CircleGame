package sprites;

import Positioning.Coordinate;
import UI.ICollisionHandler;

public class ProjectileSprite extends GameSprite {

	private Coordinate target;
	
	public ProjectileSprite(Coordinate coord, int radius, int speed, Coordinate target) {
		
		super(coord, radius, speed, java.awt.Color.BLACK);
		this.target = target;
	}
	
	@Override
	public void updatePosition() {
		//Using equation of a line y = m(x1-x) + y1
		float m = getSlope(target, this.getCoordinate());
		
		int	x = this.getX() + this.getSpeed();
		int	y = Math.round(m*(x - this.getX()) + this.getY());
		this.setCoordiante(new Coordinate(x, y));
	}
	
	private float getSlope(Coordinate coord1, Coordinate coord2){
		return (float)(coord2.getY()-coord1.getY())/(float)(coord2.getX() - coord1.getX());
	}

	@Override
	public void handleCollisionWithPlayer(ICollisionHandler handler) {
		handler.HandleCollisionSound("Lose");
		handler.HandleLose();
	}
}
