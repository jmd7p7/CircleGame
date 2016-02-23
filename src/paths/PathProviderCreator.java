package paths;

import java.util.Random;

public class PathProviderCreator {
	private Random ran;
	private int height;
	private int width;
	
	public PathProviderCreator(int width, int height){
		this.height = height;
		this.width = width;
		ran = new Random(System.nanoTime());
	}
	
	public PathProvider getPathProvider(){
		int value = ran.nextInt(2);
		
		switch(value){
		case 0:
			return new LinePathProvider(this.width, this.height);
		case 1:
			return new SinePathProvider(this.width, this.height);
		default:
			return new LinePathProvider(this.width, this.height);
		}
	}
}
