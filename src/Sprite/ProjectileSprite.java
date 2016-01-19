package Sprite;

import Positioning.Coordinate;
import org.junit.Assert;
import java.awt.Color;

import java.lang.Math;

public class ProjectileSprite extends Sprite {
	private Coordinate playerCoordinate;
	private final double speed;
	private double riseOverRun;
	private int lastXIncrease;
	private int lastYIncrease;

	public ProjectileSprite(Sprite enemySprite, PlayerSprite playerSprite, int speed) {
		super(2, Color.BLACK, enemySprite.getX_Coord() + enemySprite.getRadius(), enemySprite.getY_Coord() + enemySprite.getRadius());
		this.playerCoordinate = new Coordinate(playerSprite.getX_Coord() + playerSprite.getRadius(), 
											   playerSprite.getY_Coord() + playerSprite.getRadius());
		this.speed = speed;
	}

	public void updatePosition(Double delta) {
		int newX_coord;
		int newY_coord;
		if(getRun() > 0){
			this.riseOverRun = Math.abs(getRise()/getRun());
			if(this.riseOverRun < 1){
				newX_coord = this.getX_Coord() + (int) Math.round((delta * speed));
				newY_coord = getRise() >= 0
						? (int) Math.round((this.getY_Coord() + (int) (delta * speed) * this.riseOverRun))
						: (int) Math.round((this.getY_Coord() - (int) (delta * speed) * this.riseOverRun));
			}else{
				double runOverRise = Math.abs(getRun()/getRise());
				newX_coord = this.getX_Coord() + (int) Math.round((delta * speed)*runOverRise);
				newY_coord = getRise() >= 0
						? (int) Math.round((this.getY_Coord() + (int) (delta * speed)))
						: (int) Math.round((this.getY_Coord() - (int) (delta * speed)));
			}
			this.lastXIncrease = newX_coord - this.getX_Coord();
			this.lastYIncrease = newY_coord - this.getY_Coord();
		}
		else{
			newX_coord = this.lastXIncrease > 0 ? this.getX_Coord() + this.lastXIncrease : this.getX_Coord() + 1;
			newY_coord = this.getY_Coord() + this.lastYIncrease;
		}
		this.setCoordinate(new Coordinate(newX_coord, newY_coord));
	}

	private double getRise() {
		return (double) (playerCoordinate.getY() - this.getY_Coord());
	}

	private double getRun() {
		return (double) (playerCoordinate.getX() - this.getX_Coord());
	}
}
