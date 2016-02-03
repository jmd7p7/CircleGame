package Tests;
import paths.SlopeDownPathProvider;
import org.junit.Assert;
import org.junit.Test;

import Positioning.Coordinate;

public class SlopeDownPathProviderTests {

	@Test
	public void testGetNextCoordinate() {
		//Arrange
		int expectedY = 193;
		int x = 310;
		int y_max = 400;
		SlopeDownPathProvider provider = new SlopeDownPathProvider(y_max);
		
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}

}
