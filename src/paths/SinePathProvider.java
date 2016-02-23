package paths;
import java.lang.Math;
import Positioning.Coordinate;

public class SinePathProvider extends PathProvider {

	int yOffset;
	
	public SinePathProvider(int width, int height) {
		super(width, height);
		yOffset = PathProvider.ran.nextInt(height);
		yOffset = yOffset < 50 ? 50 : yOffset;
	}

	@Override
	public Coordinate getNextCoordinate(int x) {	
		int y = (int) Math.round((Math.sin(.035 * x) * 50 + yOffset));
		return new Coordinate(x, y);
	}

}
