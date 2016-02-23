package sprites;

import Positioning.Coordinate;
import UI.ICollisionHandler;

public class ProjectileSprite extends GameSprite {

	private Coordinate target;
	
	public ProjectileSprite(Coordinate coord, int radius, int speed, Coordinate target, int screenWidth) {
		
		super(coord, radius, speed, java.awt.Color.BLACK);
		this.target = target;
		setTargetToEndOfScreen(screenWidth);
	}
	
	//setTargetToEndOfScreen() first calculates the slope from the projectile to the target.
	//Using the calculated slope and the initial position of the projectile, the target
	//coordinate is reset with the x value being the screen width and the y value calculated from point slope form.
	//This prevents the projectile from beheving strangely after it is past the player (target)
	private void setTargetToEndOfScreen(int screenWidth) {
		float m = getSlope(target, this.getCoordinate());
		int x = screenWidth;
		this.target = new Coordinate(x, Math.round(m * (x - this.getX()) + this.getY()));
	}

	@Override
	public void updatePosition() {
		//Using equation of a line y = m(x1-x) + y1
		float m = getSlope(target, this.getCoordinate());
		if(m < 0){
			m = Math.abs(m) > 3 ? -3 : m;
		}
		else{
			m = m > 3 ? 3 : m;
		}
		int x = this.getX() + this.getSpeed();
		int y = Math.round(m * this.getSpeed() + this.getY());
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
