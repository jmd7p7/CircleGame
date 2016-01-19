package GamePlay;

import java.util.Random;

import Level.LevelGameSpriteInformation;
import Level.LevelPlayerSpriteInformation;
import Sprite.GameSpriteType;
import Sprite.GameSprite;
import Sprite.GetBiggerEdibleSprite;
import Sprite.GetSmallerEnemySprite;
import Sprite.GoFasterEdibleSprite;
import Sprite.GoSlowerEnemySprite;
import Sprite.CannonSprite;
import Sprite.EdibleSprite;
import Sprite.EnemySprite;
import Sprite.LoseSprite;
import Sprite.PlayerSprite;
import Sprite.WinSprite;

public class SpriteCreator {
	private Random ran;
	private final Boolean NEXT_EDIBLE_IS_GET_BIGGER = true;
	private final Boolean NEXT_ENEMY_IS_GET_SMALLER = true;
	private final Boolean NEXT_WINLOSE_IS_WIN = true;

	public SpriteCreator(){
		ran = new Random(System.currentTimeMillis());
	}
	
	public PlayerSprite enhancePlayerSprite(PlayerSprite oldPlayer, EdibleSprite edibleSprite,
			LevelPlayerSpriteInformation playerSpriteInformation){
		
		int newSpeed = oldPlayer.getVerticalSpeed() + edibleSprite.getSpeedIncreaseReward();
		newSpeed = newSpeed > playerSpriteInformation.getMaxPlayerSpeed() ? 
				playerSpriteInformation.getMaxPlayerSpeed() : newSpeed;
				
		int newRadius = oldPlayer.getRadius() + edibleSprite.getSizeIncreaseReward();
		newRadius = newRadius > playerSpriteInformation.getMaxRadius() ? 
				playerSpriteInformation.getMaxRadius() : newRadius;
				
		return new PlayerSprite(newRadius, oldPlayer.getX_Coord(), oldPlayer.getY_Coord(), newSpeed);
	}
	
	public PlayerSprite diminishPlayerSprite(PlayerSprite oldPlayer, EnemySprite enemySprite, 
			LevelPlayerSpriteInformation playerSpriteInformation){
		
		int newSpeed = oldPlayer.getVerticalSpeed() - enemySprite.getSpeedDecreasePenalty();
		newSpeed = newSpeed < playerSpriteInformation.getMinPlayerSpeed() ? 
				playerSpriteInformation.getMinPlayerSpeed() : newSpeed;
				
		int newRadius = oldPlayer.getRadius() - enemySprite.getSizeDecreasePenalty();
		newRadius = newRadius < playerSpriteInformation.getMinRadius() ? 
				playerSpriteInformation.getMinRadius() : newRadius;
				
		return new PlayerSprite(newRadius, oldPlayer.getX_Coord(), oldPlayer.getY_Coord(), newSpeed);
	}

	public GameSprite createNewGameSprite(GameSpriteType type, int screenHeight, 
			LevelGameSpriteInformation gameSpriteInformation, long incubationPeriod) {
		
		int randomStartingHeight = ran.nextInt(screenHeight);		
		int randomSpriteRadius = getRandomFromRange(gameSpriteInformation.getMinRadius(), 
				gameSpriteInformation.getMaxRadius());				
		int randomHorizontalSpeed = getRandomFromRange(gameSpriteInformation.getMinGameSpriteSpeed(), 
				gameSpriteInformation.getMaxGameSpriteSpeed());		
		int randomVerticalSpeed = getRandomFromRange(gameSpriteInformation.getMinGameSpriteSpeed(), 
				gameSpriteInformation.getMaxGameSpriteSpeed());

		switch (type) { 
		case EDIBLE:
			if (ran.nextBoolean() == NEXT_EDIBLE_IS_GET_BIGGER) {
				return new GetBiggerEdibleSprite(randomSpriteRadius, randomHorizontalSpeed, randomVerticalSpeed,
						randomStartingHeight, incubationPeriod);
			} else {
				return new GoFasterEdibleSprite(randomSpriteRadius, randomHorizontalSpeed, randomVerticalSpeed,
						randomStartingHeight, incubationPeriod);
			}
		case ENEMY:
			if (ran.nextBoolean() == NEXT_ENEMY_IS_GET_SMALLER) {
				return new GetSmallerEnemySprite(randomSpriteRadius, randomHorizontalSpeed, randomVerticalSpeed,
						randomStartingHeight, incubationPeriod);
			} else {
				return new GoSlowerEnemySprite(randomSpriteRadius, randomHorizontalSpeed, randomVerticalSpeed,
						randomStartingHeight, incubationPeriod);
			}
		case CANNON:
			return new CannonSprite(randomSpriteRadius, randomHorizontalSpeed, randomVerticalSpeed,
					randomStartingHeight, gameSpriteInformation.getMaxNanoSecondsBetweenFires(), incubationPeriod);
		case WINLOSE:
			if (ran.nextBoolean() == NEXT_WINLOSE_IS_WIN) {
				return new WinSprite(randomSpriteRadius, randomHorizontalSpeed, randomVerticalSpeed,
						randomStartingHeight, incubationPeriod);
			}

			else {
				return new LoseSprite(randomSpriteRadius, randomHorizontalSpeed, randomVerticalSpeed,
						randomStartingHeight, incubationPeriod);
			}
		default:
			return new GetBiggerEdibleSprite(randomSpriteRadius, randomHorizontalSpeed, randomVerticalSpeed,
					randomStartingHeight, incubationPeriod);
		}
	}
	
	private int getRandomFromRange(int lowerBoundInclusive, int upperBoundInclusive){
		int result = ran.nextInt(upperBoundInclusive + 1);
		result = result < lowerBoundInclusive ? lowerBoundInclusive : result;
		return result;
	}
}
