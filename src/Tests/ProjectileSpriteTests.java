package Tests;
import org.junit.Assert;

import Positioning.Coordinate;
import sprites.ProjectileSprite;

import org.junit.Test;

public class ProjectileSpriteTests {

	@Test
	public void testUpdatePosition() {
		//Arrange
		Coordinate targetPosition = new Coordinate(5,4);
		Coordinate currentPosition = new Coordinate(1,2);
		int speed = 1;
		ProjectileSprite projectile = 
				new ProjectileSprite(currentPosition, 4, speed, targetPosition);
		int expectedY = 3;
		
		//Act
		projectile.updatePosition();
		
		//Assert
		Assert.assertEquals(expectedY, projectile.getY());
		
	}

}
