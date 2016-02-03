package paths;
import java.lang.Math;
import Positioning.Coordinate;

public class AbsoluteValuePathProvider extends PathProvider {
	
	public AbsoluteValuePathProvider(int y_max) {
		super(y_max);
	}

	@Override
	public Coordinate getNextCoordinate(int x) {
		int y = (int) Math.round(Math.abs(1.25 * x - .97 * this.y_max) + 5);
		return new Coordinate(x, y);
	}

}
