package paths;
import java.lang.Math;
import Positioning.Coordinate;

public class ParabolaDownPathProvider extends PathProvider {
	
	public ParabolaDownPathProvider(int y_max) {
		super(y_max);
	}

	@Override
	public Coordinate getNextCoordinate(int x) {
		int y = (int) Math.round(-((.05 * x - Math.sqrt(.99*this.y_max))*(.05 * x - Math.sqrt(.99*this.y_max))) + .99*this.y_max);
		return new Coordinate(x,y);
	}

}
