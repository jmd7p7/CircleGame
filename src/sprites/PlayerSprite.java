package sprites;

import Positioning.Coordinate;

public class PlayerSprite extends Sprite {
	public PlayerSprite(Coordinate coord, int radius, int speed){
		super(coord, radius, speed, java.awt.Color.CYAN);
	}
	
	public void moveUp(){
		int newY = this.getY() - this.getSpeed();
		this.setCoordiante(new Coordinate(this.getX(), newY));
	}
	
	public void moveDown(){
		int newY = this.getY() + this.getSpeed();
		this.setCoordiante(new Coordinate(this.getX(), newY));
	}
}
