package sprites;

import java.util.Random;
import Utility.Utility;
import Level.LevelGameSpriteInformation;
import Positioning.Coordinate;
import UI.ICannonFireHandler;
import paths.PathProviderCreator;

public class SpriteCreator {
	Random ran;
	PathProviderCreator pathProviderCreator;
	
	public SpriteCreator(int yMax){
		this.ran = new Random(System.nanoTime());
		this.pathProviderCreator = new PathProviderCreator(yMax);
	}
	
	public GameSprite getRandomGameSprite(LevelGameSpriteInformation gameSpriteInfo, ICannonFireHandler cannonFireHandler){
		
		int radius = ran.nextInt(gameSpriteInfo.getMaxRadius()+1);
		radius = Utility.checkAgainstMinAndMaxValues(radius, gameSpriteInfo.getMaxRadius(), gameSpriteInfo.getMinRadius());
		
		int speed = ran.nextInt(gameSpriteInfo.getMaxGameSpriteSpeed()+1);
		speed = Utility.checkAgainstMinAndMaxValues(speed, gameSpriteInfo.getMaxGameSpriteSpeed(), gameSpriteInfo.getMinGameSpriteSpeed());
		
		Coordinate beginningCoordinate = new Coordinate(-(radius), 0);
		
		int value = ran.nextInt(6);
		switch(value){
		case 0:
			return new ShrinkPlayerSprite(beginningCoordinate, radius, speed, 
					pathProviderCreator.getPathProvider());
		case 1:
			return new SlowDownPlayerSprite(beginningCoordinate, radius, speed, 
					pathProviderCreator.getPathProvider());
		case 2:
			return new SpeedupPlayerSprite(beginningCoordinate, radius, speed, 
					pathProviderCreator.getPathProvider());
		case 3:
			return new EnlargePlayerSprite(beginningCoordinate, radius, speed, 
					pathProviderCreator.getPathProvider());
		case 4:
			return new CannonSprite(beginningCoordinate, radius, speed, 
					pathProviderCreator.getPathProvider(), cannonFireHandler);
		case 5:
			return new WinSprite(beginningCoordinate, radius, speed, 
					pathProviderCreator.getPathProvider());
		default:
			return new WinSprite(beginningCoordinate, radius, speed, 
					pathProviderCreator.getPathProvider());
		}
	}
}
