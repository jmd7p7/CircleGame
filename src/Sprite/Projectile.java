package Sprite;
import Positioning.Coordinate;
import org.junit.Assert;
import java.awt.Color;


import java.lang.Math;


public class Projectile {
	private Coordinate coordinate;
	private int radius;
	private double riseOverRun;
	private final double maxRiseOverRun = 1.750;
	private final double rise;
	private final double run;
	private final int speed;
	private Color color;
	
	public Projectile(Sprite enemySprite, Sprite playerSprite, int speed){
		Assert.assertNotNull("enemySprite cannot be null in Projectile contstructor", enemySprite);
		Assert.assertNotNull("playerSprite cannot be null in Projectile contstructor", playerSprite);
		Assert.assertTrue("speed cannot be negative in Projectile constructor.", speed >= 0);
		
		this.coordinate = 
				new Coordinate(enemySprite.getX_Coord() + enemySprite.getRadius(), 
							   enemySprite.getY_Coord() + enemySprite.getRadius());
		this.radius = 2;
		this.rise = playerSprite.getY_Coord() - enemySprite.getY_Coord();
		this.run = playerSprite.getX_Coord() - enemySprite.getX_Coord();
		double potentialRiseOverRun = setRiseOverRun(enemySprite, playerSprite);
		this.riseOverRun = potentialRiseOverRun < this.maxRiseOverRun ? potentialRiseOverRun : this.maxRiseOverRun;
		this.speed = speed;
		this.color = Color.BLACK;
	}

	private double setRiseOverRun(Sprite enemySprite, Sprite playerSprite) {
		return (double)(playerSprite.getY_Coord() - enemySprite.getY_Coord()) / 
			   (playerSprite.getX_Coord() - enemySprite.getX_Coord());
	}
	
	public int getX_coord(){
		return this.coordinate.getX();
	}
	
	public int getY_coord(){
		return this.coordinate.getY();
	}
	
	public int getRadius(){
		return this.radius;
	}
	
	public void updatePosition(Double delta){
		int newX_coord = this.run >= 0 ? this.getX_coord() + (int) (delta*speed) : 
								         this.getX_coord() - (int) (delta*speed);	
		int newY_coord = this.run >= 0 ? (int) Math.round((this.getY_coord() + (int) (delta*speed) * this.riseOverRun)) :
			(int) Math.round((this.getY_coord() - (int) (delta*speed) * this.riseOverRun));
		this.coordinate = new Coordinate(newX_coord, newY_coord);
		System.out.println("x: " + newX_coord + " y:" + newY_coord);
	}

	public Color getColor() {
		return this.color;
	}
}
