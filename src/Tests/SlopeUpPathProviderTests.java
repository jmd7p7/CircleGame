package Tests;

import org.junit.Assert;
import paths.SlopeUpPathProvider;
import org.junit.Test;

import Positioning.Coordinate;

public class SlopeUpPathProviderTests {
	
	@Test
	public void testGetNextCoordinate() {
		//Arrange
		int expectedY = 133;
		int x = 200;
		int y_max = 600;
		SlopeUpPathProvider provider = new SlopeUpPathProvider(y_max);
		
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}
}
