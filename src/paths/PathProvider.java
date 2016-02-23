package paths;

import java.util.Random;

import Positioning.Coordinate;

public abstract class PathProvider {
	protected int width;
	protected int height;
	protected static Random ran = new Random(System.nanoTime());
	
	public PathProvider(int width, int height){
		this.height = height;
		this.width = width;
	}
	
	public abstract Coordinate getNextCoordinate(int x);
}
