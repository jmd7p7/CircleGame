package paths;

import Positioning.Coordinate;
import java.lang.Math;

public class TornadoPathProvider extends PathProvider {

	public TornadoPathProvider(int y_max) {
		super(y_max);
	}
	
	@Override
	public Coordinate getNextCoordinate(int x) {
		int y = (int) Math.round(Math.sqrt(.003 * x)*Math.abs(.2 * x) * Math.sin(.03 * x) + this.y_max/2);
		return new Coordinate(x,y);
	}

}
