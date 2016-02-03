package paths;
import java.lang.Math;
import Positioning.Coordinate;

public class SlopeUpPathProvider extends PathProvider {

	public SlopeUpPathProvider(int y_max) {
		super(y_max);
	}
	
	@Override
	public Coordinate getNextCoordinate(int x) {
		int y = (int)Math.round((2.0/3.0)*x);
		return new Coordinate(x, y);
	}

}
