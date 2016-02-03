package Tests;
import paths.SineIncreasingAmplitudePathProvider;
import Positioning.Coordinate;
import org.junit.Assert;

import org.junit.Test;

public class SineIncreasingAmplitudePathProviderTests {

	@Test
	public void testGetNextCoordinateMaxHeightFourHundred() {
		//Arrange
		int expectedY = 572;
		int x = 50;
		int y_max = 800;
		SineIncreasingAmplitudePathProvider provider = new SineIncreasingAmplitudePathProvider(y_max);	
		
		//Act
		Coordinate nextCoordinate = provider.getNextCoordinate(x);
		
		//Assert
		Assert.assertEquals(expectedY, nextCoordinate.getY());
	}

}
