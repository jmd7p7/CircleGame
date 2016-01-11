package Tests;

import static org.junit.Assert.*;
import Utility.Utility;

import org.junit.Assert;
import org.junit.Test;

public class DistanceFormulaTests {

	@Test
	public void testForCorrectOutputFromValidInputs() {
		//arrange
		double x1_coord = 7;
		double y1_coord = 10;
		double x2_coord = 2;
		double y2_coord = 6;
		Double correctValue = Math.sqrt(41);
		
		//act
		Double distance = Utility.getDistance(x1_coord, y1_coord, x2_coord, y2_coord);
		
		//assert
		Assert.assertEquals(correctValue, distance);
	}

}
