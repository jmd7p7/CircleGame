package paths;

import Positioning.Coordinate;

public class LinePathProvider extends PathProvider {

	float slope; 
	int yIntercept; 
	
	public LinePathProvider(int width, int height) {
		super(width, height);
		
		this.yIntercept = PathProvider.ran.nextInt(this.height);
		int finalY = PathProvider.ran.nextInt(this.height);
		this.slope = (((float)(finalY - yIntercept)/(float)this.width));
	}

	@Override
	public Coordinate getNextCoordinate(int x) {
		int y = Math.round((this.slope * x + this.yIntercept));
		return new Coordinate(x, y);
	}

}
