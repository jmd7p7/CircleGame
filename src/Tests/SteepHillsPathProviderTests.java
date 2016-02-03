package Tests;

import org.junit.Assert;
import org.junit.Test;

import Positioning.Coordinate;
import paths.SteepHillsPathProvider;

public class SteepHillsPathProviderTests {

	@Test
	public void testGetNextCoordinate() {
		//Arrange
		int expectedY = 431;
		int x = 800;
		int y_max = 600;
		SteepHillsPathProvider provider = new SteepHillsPathProvider(y_max);
		
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}

}
