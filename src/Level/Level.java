package Level;

public class Level implements LevelPlayerSpriteInformation, LevelGameSpriteInformation, 
							  LevelCannonSpriteInformation, LevelProjectileSpriteInformation{
	private int level;
	private int numEnemySprites;
	private int numEdibleSprites;
	private int projectileSpeed;
	private int maxNanoSecondsBetweenCannonFires;
	private int maxGameSpriteSpeed;
	private int minGameSpriteSpeed;
	private int maxPlayerSpriteSpeed;
	private int minPlayerSpriteSpeed;
	private int beginningPlayerSpriteSpeed;
	private int maxRadius;
	private int minRadius;
	private int playerBeginningRadius;
	
	public int getLevel() {
		return level;
	}

	public int getNumEnemySprites() {
		return numEnemySprites;
	}

	public int getNumEdibleSprites() {
		return numEdibleSprites;
	}

	
	public static Level getLevelOne(){
		Level FirstLevel = new Level();
		FirstLevel.level = 1;
		FirstLevel.numEnemySprites = 2;
		FirstLevel.numEdibleSprites = 3;
		FirstLevel.projectileSpeed = 3;
		FirstLevel.maxNanoSecondsBetweenCannonFires = 1000000000;
		FirstLevel.maxGameSpriteSpeed = 5;
		FirstLevel.minGameSpriteSpeed = 2;
		FirstLevel.maxPlayerSpriteSpeed = 7;
		FirstLevel.minPlayerSpriteSpeed = 2;
		FirstLevel.beginningPlayerSpriteSpeed = 3;
		FirstLevel.maxRadius = 20;
		FirstLevel.minRadius = 5;
		FirstLevel.playerBeginningRadius = 10;
		return FirstLevel;
	}
	
	public static Level getNextLevel(Level currentLevel){
		Level NextLevel = new Level();
		NextLevel.level = currentLevel.level +1;
		
		//If the next level is even, the strength of certain enemy attributes 
		//are doubly increased
		Boolean doubleIncrementEnemy = NextLevel.level % 2 == 0 ? true : false;
		NextLevel.numEnemySprites = doubleIncrementEnemy ? 
				currentLevel.numEnemySprites + 2 : currentLevel.numEnemySprites + 1;
		NextLevel.numEdibleSprites = currentLevel.numEdibleSprites +1;
		NextLevel.projectileSpeed = doubleIncrementEnemy == true ? 
				currentLevel.projectileSpeed + 2 : currentLevel.projectileSpeed + 1;
		NextLevel.maxNanoSecondsBetweenCannonFires = doubleIncrementEnemy == true ?
				currentLevel.maxNanoSecondsBetweenCannonFires - 100000000 : 
				currentLevel.maxNanoSecondsBetweenCannonFires - 50000000;
		NextLevel.maxGameSpriteSpeed = currentLevel.maxGameSpriteSpeed + 1;
		NextLevel.minGameSpriteSpeed = currentLevel.minGameSpriteSpeed + 1;
		NextLevel.maxPlayerSpriteSpeed = doubleIncrementEnemy == true ?
				currentLevel.maxPlayerSpriteSpeed + 2 : currentLevel.maxPlayerSpriteSpeed + 1;
		NextLevel.minPlayerSpriteSpeed = doubleIncrementEnemy == true ?
				currentLevel.minPlayerSpriteSpeed + 2 : currentLevel.minPlayerSpriteSpeed + 1;
		NextLevel.beginningPlayerSpriteSpeed = doubleIncrementEnemy == true ?
				currentLevel.beginningPlayerSpriteSpeed + 2 : currentLevel.beginningPlayerSpriteSpeed +1;
		NextLevel.maxRadius = currentLevel.maxRadius + 2;
		NextLevel.minRadius = currentLevel.minRadius +2;
		NextLevel.playerBeginningRadius = currentLevel.playerBeginningRadius;
		return NextLevel;
	}

	@Override
	public int getPlayerBeginningSpeed() {
		return this.beginningPlayerSpriteSpeed;
	}

	@Override
	public int getPlayerBeginningRadius() {
		return this.playerBeginningRadius;
	}

	@Override
	public int getMinPlayerSpeed() {
		return this.minPlayerSpriteSpeed;
	}

	@Override
	public int getMaxPlayerSpeed() {
		return this.maxPlayerSpriteSpeed;
	}

	@Override
	public int getMaxRadius() {
		return this.maxRadius;
	}

	@Override
	public int getMinRadius() {
		return this.minRadius;
	}

	@Override
	public int getMaxGameSpriteSpeed() {
		return this.maxGameSpriteSpeed;
	}

	@Override
	public int getMinGameSpriteSpeed() {
		return this.minGameSpriteSpeed;
	}

	@Override
	public int getMaxNanoSecondsBetweenFires() {
		return this.maxNanoSecondsBetweenCannonFires;
	}

	@Override
	public int getProjectileSpeed() {
		return this.projectileSpeed;
	}
}
