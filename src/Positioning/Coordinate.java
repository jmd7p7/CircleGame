package Positioning;

import org.junit.Assert;

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y){
		Assert.assertTrue("x coordinate cannot be negative.", x >= 0);
		Assert.assertTrue("y coordinate cannot be negative.", y >= 0);
		
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
}
