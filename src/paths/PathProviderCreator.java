package paths;

import java.util.Random;

public class PathProviderCreator {
	private Random ran;
	private int yMax;
	
	public PathProviderCreator(int yMax){
		this.yMax = yMax;
		ran = new Random(System.nanoTime());
	}
	
	public PathProvider getPathProvider(){
		int value = ran.nextInt(7);
		
		switch(value){
		case 0:
			return new AbsoluteValuePathProvider(this.yMax);
		case 1:
			return new ParabolaDownPathProvider(this.yMax);
		case 2:
			return new SineIncreasingAmplitudePathProvider(this.yMax);
		case 3:
			return new SlopeDownPathProvider(this.yMax);
		case 4:
			return new SlopeUpPathProvider(this.yMax);
		case 5:
			return new SteepHillsPathProvider(this.yMax);
		case 6:
			return new TornadoPathProvider(this.yMax);
		default:
			return new AbsoluteValuePathProvider(this.yMax);
		}
	}
}
