package paths;

import Positioning.Coordinate;
import java.lang.Math;

public class SteepHillsPathProvider extends PathProvider {
	
	public SteepHillsPathProvider(int y_max) {
		super(y_max);
	}

	@Override
	public Coordinate getNextCoordinate(int x) {
		int y = (int) Math.round(Math.cbrt(Math.sin(Math.sqrt(.2*x)))*this.y_max/2 + this.y_max/2);
		return new Coordinate(x, y);
	}

}
