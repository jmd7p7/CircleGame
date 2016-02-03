package Tests;

import org.junit.Test;
import Utility.Utility;
import org.junit.Assert;

public class CheckAgainstMinAndMaxValuesTests {
	
	@Test
	public void CheckAgainstMinAndMaxValue_ValueWithinLimits(){
		//Arrange
		int value = 5;
		int maxValue = 6;
		int minValue = 4;
		
		//Act
		int result = Utility.checkAgainstMinAndMaxValues(value, maxValue, minValue);
		
		//Assert
		Assert.assertEquals(5, result);	
	}
	
	@Test
	public void CheckAgainstMinAndMaxValue_ValueOverMax(){
		//Arrange
		int value = 7;
		int maxValue = 6;
		int minValue = 4;
		
		//Act
		int result = Utility.checkAgainstMinAndMaxValues(value, maxValue, minValue);
		
		//Assert
		Assert.assertEquals(6, result);	
	}
	
	@Test
	public void CheckAgainstMinAndMaxValue_ValueUnderMin(){
		//Arrange
		int value = 3;
		int maxValue = 6;
		int minValue = 4;
		
		//Act
		int result = Utility.checkAgainstMinAndMaxValues(value, maxValue, minValue);
		
		//Assert
		Assert.assertEquals(4, result);	
	}
}
