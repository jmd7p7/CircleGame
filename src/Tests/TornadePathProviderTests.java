package Tests;

import org.junit.Assert;
import org.junit.Test;
import Positioning.Coordinate;
import paths.TornadoPathProvider;

public class TornadePathProviderTests {

	@Test
	public void testGetNextCoordinateMaxHeightFourHundred() {
		//Arrange
		int expectedY = 180;
		int x = 150;
		int y_max = 400;
		TornadoPathProvider provider = new TornadoPathProvider(y_max);
		
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}

}
