package Sprite;
import java.awt.Color;
public class CannonSprite extends GameSprite{
	
	private final int maxNanoSecondsBetweenFires;
	private long lastFireTime;
	
	public CannonSprite(int radius, int horizontalSpeed, int verticalSpeed, int startingY_coord, int maxNanoSecondsBetweenFires, long gestationPeriod){
		super(radius, java.awt.Color.BLACK, horizontalSpeed, verticalSpeed, GameSpriteType.CANNON, startingY_coord, gestationPeriod);
		this.maxNanoSecondsBetweenFires = maxNanoSecondsBetweenFires;
		this.lastFireTime = System.nanoTime();
	}
	
	public Boolean FireCannon(){
		if(System.nanoTime() - lastFireTime > maxNanoSecondsBetweenFires){
			lastFireTime = System.nanoTime();
			return true;
		}
		return false;
	}
	
}
