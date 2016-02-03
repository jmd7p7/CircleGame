package Tests;

import org.junit.Assert;
import org.junit.Test;

import Positioning.Coordinate;
import paths.ParabolaDownPathProvider;

public class ParabolaDownPathProviderTests {

	@Test
	public void testGetNextCoordinateSmallScreen() {
		//Arrange
		int expectedY = 155;
		int x = 150;
		int y_max = 200;
		ParabolaDownPathProvider provider = new ParabolaDownPathProvider(y_max);
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}
	
	@Test
	public void testGetNextCoordinateMediumScreen() {
		//Arrange
		int expectedY = 361;
		int x = 516;
		int y_max = 400;
		ParabolaDownPathProvider provider = new ParabolaDownPathProvider(y_max);
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}
	
	@Test
	public void testGetNextCoordinateLargeScreen() {
		//Arrange
		int expectedY = 555;
		int x = 362;
		int y_max = 600;
		ParabolaDownPathProvider provider = new ParabolaDownPathProvider(y_max);
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}
	
	@Test
	public void testGetNextCoordinateExtraLargeScreen() {
		//Arrange
		int expectedY = 8;
		int x = 1050;
		int y_max = 700;
		ParabolaDownPathProvider provider = new ParabolaDownPathProvider(y_max);
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}

}
