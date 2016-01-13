package Sprite;

import Positioning.Coordinate;
import org.junit.Assert;
import java.awt.Color;

import java.lang.Math;

public class Projectile {
	private Coordinate coordinate;
	private Coordinate playerCoordinate;
	private int radius;
	private final double maxRiseOverRun = 4.00;
	private final double speed;
	private Color color;
	private double riseOverRun;
	private int lastXIncrease;
	private int lastYIncrease;

	public Projectile(Sprite enemySprite, PlayerSprite playerSprite, int speed) {
		Assert.assertNotNull("enemySprite cannot be null in Projectile contstructor", enemySprite);
		Assert.assertTrue("speed cannot be negative in Projectile constructor.", speed >= 0);

		this.coordinate = new Coordinate(enemySprite.getX_Coord() + enemySprite.getRadius(),
				enemySprite.getY_Coord() + enemySprite.getRadius());
		this.playerCoordinate = new Coordinate(playerSprite.getX_Coord() + playerSprite.getRadius()*2, 
											   playerSprite.getY_Coord() + playerSprite.getRadius()*2);
		this.radius = 2;
		this.speed = speed;
		this.color = Color.BLACK;
	}

	public int getX_coord() {
		return this.coordinate.getX();
	}

	public int getY_coord() {
		return this.coordinate.getY();
	}

	public int getRadius() {
		return this.radius;
	}

	public void updatePosition(Double delta) {
		int newX_coord;
		int newY_coord;
		if(getRun() > 0){
			this.riseOverRun = Math.abs(getRise()/getRun());
			if(this.riseOverRun < 1){
				newX_coord = this.getX_coord() + (int) Math.round((delta * speed));
				newY_coord = getRise() >= 0
						? (int) Math.round((this.getY_coord() + (int) (delta * speed) * this.riseOverRun))
						: (int) Math.round((this.getY_coord() - (int) (delta * speed) * this.riseOverRun));
			}else{
				double runOverRise = Math.abs(getRun()/getRise());
				newX_coord = this.getX_coord() + (int) Math.round((delta * speed)*runOverRise);
				newY_coord = getRise() >= 0
						? (int) Math.round((this.getY_coord() + (int) (delta * speed)))
						: (int) Math.round((this.getY_coord() - (int) (delta * speed)));
			}
			this.lastXIncrease = newX_coord - this.getX_coord();
			this.lastYIncrease = newY_coord - this.getY_coord();
		}
		else{
			newX_coord = this.lastXIncrease > 0 ? this.getX_coord() + this.lastXIncrease : this.getX_coord() + 1;
			newY_coord = this.getY_coord() + this.lastYIncrease;
		}
		this.coordinate = new Coordinate(newX_coord, newY_coord);
	}

	private double getRise() {
		return (double) (playerCoordinate.getY() - this.coordinate.getY());
	}

	private double getRun() {
		return (double) (playerCoordinate.getX() - this.coordinate.getX());
	}

	public Color getColor() {
		return this.color;
	}
}
