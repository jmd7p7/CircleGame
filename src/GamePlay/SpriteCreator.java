package GamePlay;
import java.util.Random;

import Sprite.GameSpriteType;
import Sprite.GameSprite;
import Sprite.GetBiggerEdibleSprite;
import Sprite.GetSmallerEnemySprite;
import Sprite.GoFasterEdibleSprite;
import Sprite.GoSlowerEnemySprite;
import Sprite.LoseSprite;
import Sprite.WinSprite;

public class SpriteCreator {
	private Random ran;
	private final Boolean NEXT_EDIBLE_IS_GET_BIGGER = true;
	private final Boolean NEXT_ENEMY_IS_GET_SMALLER = true;
	private final Boolean NEXT_WINLOSE_IS_WIN = true;
	private final int MAX_HEIGHT_WIDTH = 100;
	private final int MAX_RADIUS = 50;
	private final int MIN_HEIGHT_WIDTH = 5;
	private final int MIN_RADIUS = 4;
	private final int MAX_GAMESPRITE_SPEED = 10;
	private final int MIN_GAMESPRITE_SPEED = 2;
	
	
	private SpriteCreator(){
		ran = new Random(System.currentTimeMillis());
	}
	
	// Implementing Singleton
	private static SpriteCreator instance = null;

	public static SpriteCreator getInstance(){
		if (instance == null) {
			synchronized (SpriteCreator.class) {
				if (instance == null) {
					instance = new SpriteCreator();
				}
			}
		}
		return instance;
	}
	
	public GameSprite createNewGameSprite(GameSpriteType type, int screenHeight){
		int randomStartingHeight = ran.nextInt(screenHeight);	
		int randomSpiteRadius = ran.nextInt(MAX_RADIUS + 1);
		int randomHorizontalSpeed = ran.nextInt(MAX_GAMESPRITE_SPEED);
		randomHorizontalSpeed = 
				randomHorizontalSpeed < MIN_GAMESPRITE_SPEED ? MIN_GAMESPRITE_SPEED : randomHorizontalSpeed;
		int randomVerticalSpeed = ran.nextInt(MAX_GAMESPRITE_SPEED);
		randomVerticalSpeed = 
				randomVerticalSpeed < MIN_GAMESPRITE_SPEED ? MIN_GAMESPRITE_SPEED : randomVerticalSpeed;
		
		switch(type){
		case EDIBLE:
			if(ran.nextBoolean() == NEXT_EDIBLE_IS_GET_BIGGER){
				return new GetBiggerEdibleSprite(randomSpiteRadius, randomHorizontalSpeed, 
						                         randomVerticalSpeed, randomStartingHeight);
			}
			else{
				return new GoFasterEdibleSprite(randomSpiteRadius, randomHorizontalSpeed, 
												randomVerticalSpeed, randomStartingHeight);
			}
		case ENEMY:
			if(ran.nextBoolean() == NEXT_ENEMY_IS_GET_SMALLER){
				return new GetSmallerEnemySprite(randomSpiteRadius, randomHorizontalSpeed, 
												 randomVerticalSpeed, randomStartingHeight);
			}
			else{
				return new GoSlowerEnemySprite(randomSpiteRadius, randomHorizontalSpeed, 
											   randomVerticalSpeed, randomStartingHeight);
			}
		case WINLOSE:
			if(ran.nextBoolean() == NEXT_WINLOSE_IS_WIN){
				return new WinSprite(randomSpiteRadius, randomHorizontalSpeed, 
								     randomVerticalSpeed, randomStartingHeight);
			}
			else{
				return new LoseSprite(randomSpiteRadius, randomHorizontalSpeed, 
									  randomVerticalSpeed, randomStartingHeight);
			}
		default:
			return new GetBiggerEdibleSprite(randomSpiteRadius, randomHorizontalSpeed, 
											 randomVerticalSpeed, randomStartingHeight);
		}
	}

}
