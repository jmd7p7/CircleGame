package Sprite;
import Positioning.Coordinate;


public class Projectile {
	private Coordinate coordinate;
	private int length;
	private double trajectory;
	
	public Projectile(Sprite enemySprite, Sprite playerSprite){
		this.coordinate = 
				new Coordinate(enemySprite.getX_Coord() + enemySprite.getRadius() * 2, 
							   enemySprite.getY_Coord() + enemySprite.getRadius());
		this.length = 4;
		this.trajectory = setTrajectory(enemySprite, playerSprite);
		
	}

	private double setTrajectory(Sprite enemySprite, Sprite playerSprite) {
		return (playerSprite.getY_Coord() - enemySprite.getY_Coord()) / 
			   (playerSprite.getX_Coord() - enemySprite.getX_Coord());
	}
	
}
