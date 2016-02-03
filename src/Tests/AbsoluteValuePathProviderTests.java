package Tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import Positioning.Coordinate;
import paths.AbsoluteValuePathProvider;

public class AbsoluteValuePathProviderTests {

	@Test
	public void testGetNextCoordinateSmallScreen() {
		//Arrange
		int expectedY = 104;
		int x = 234;
		int y_max = 200;
		AbsoluteValuePathProvider provider = new AbsoluteValuePathProvider(y_max);
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}
	
	@Test
	public void testGetNextCoordinateMediumScreen() {
		//Arrange
		int expectedY = 164;
		int x = 183;
		int y_max = 400;
		AbsoluteValuePathProvider provider = new AbsoluteValuePathProvider(y_max);
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}
	
	@Test
	public void testGetNextCoordinateLargeScreen() {
		//Arrange
		int expectedY = 193;
		int x = 616;
		int y_max = 600;
		AbsoluteValuePathProvider provider = new AbsoluteValuePathProvider(y_max);
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}
	
	@Test
	public void testGetNextCoordinateExtraLargeScreen() {
		//Arrange
		int expectedY = 464;
		int x = 910;
		int y_max = 700;
		AbsoluteValuePathProvider provider = new AbsoluteValuePathProvider(y_max);
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}

}
