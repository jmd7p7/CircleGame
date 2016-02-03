package paths;

import Positioning.Coordinate;
import java.lang.Math;

public class SineIncreasingAmplitudePathProvider extends PathProvider {

	public SineIncreasingAmplitudePathProvider(int y_max) {
		super(y_max);
	}

	@Override
	public Coordinate getNextCoordinate(int x) {
		int y = (int) Math.round(Math.log10(x) * Math.sin(.02 * x) * .5*this.y_max/3.3333 + .5*this.y_max);
		return new Coordinate(x, y);
	}

}
