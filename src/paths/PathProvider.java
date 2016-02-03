package paths;

import Positioning.Coordinate;

public abstract class PathProvider {
	int y_max;
	
	public PathProvider(int y_max){
		this.y_max = y_max;
	}
	
	public abstract Coordinate getNextCoordinate(int x);
}
