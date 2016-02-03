package paths;
import java.lang.Math;
import Positioning.Coordinate;

public class SlopeDownPathProvider extends PathProvider {

	public SlopeDownPathProvider(int y_max) {
		super(y_max);
	}
	
	@Override
	public Coordinate getNextCoordinate(int x) {
		int y = (int)-Math.round((2.0/3.0)*x) + this.y_max;
		return new Coordinate(x, y);
	}

}
