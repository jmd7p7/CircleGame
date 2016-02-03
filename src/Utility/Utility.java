package Utility;

public class Utility {
	public static Double getDistance(Double x1, Double y1, Double x2, Double y2){
		Double x_difference = Math.abs(x2 - x1);
		Double y_difference = Math.abs(y2 - y1);		
		return Math.sqrt(x_difference * x_difference + y_difference * y_difference);
	}
	
	public static int checkAgainstMinAndMaxValues(int value, int maxValue, int minValue){
		value = value < minValue ? minValue : value;
		value = value > maxValue ? maxValue : value;
		return value;
	}
}
