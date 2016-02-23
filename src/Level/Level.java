package Level;

public class Level implements ILevelPlayerSpriteInformation, ILevelGameSpriteInformation{
	private int level;
	private int numGameSprites;
	private int projectileSpeed;
	private int maxMilliSecondsBetweenCannonFires;
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

	public int getNumGameSprites() {
		return numGameSprites;
	}

	
	public static Level getLevelOne(){
		Level FirstLevel = new Level();
		FirstLevel.level = 1;
		FirstLevel.numGameSprites = 5;
		FirstLevel.projectileSpeed = 4;
		FirstLevel.maxMilliSecondsBetweenCannonFires = 3000;
		FirstLevel.maxGameSpriteSpeed = 3;
		FirstLevel.minGameSpriteSpeed = 2;
		FirstLevel.maxPlayerSpriteSpeed = 7;
		FirstLevel.minPlayerSpriteSpeed = 2;
		FirstLevel.beginningPlayerSpriteSpeed = 3;
		FirstLevel.maxRadius = 20;
		FirstLevel.minRadius = 5;
		FirstLevel.playerBeginningRadius = 10;
		return FirstLevel;
	}
	
	public static Level getNextLevel(Level previousLevel){
		Level NextLevel = new Level();
		NextLevel.level = previousLevel.level +1;
		
		//If the next level is even, the strength of certain enemy attributes 
		//are doubly increased
		Boolean doubleIncrementEnemy = NextLevel.level % 2 == 0 ? true : false;
		NextLevel.numGameSprites = doubleIncrementEnemy ? 
				previousLevel.numGameSprites + 2 : previousLevel.numGameSprites + 1;
		NextLevel.projectileSpeed = doubleIncrementEnemy == true ? 
				previousLevel.projectileSpeed + 2 : previousLevel.projectileSpeed + 1;
		NextLevel.maxMilliSecondsBetweenCannonFires = doubleIncrementEnemy == true ?
				previousLevel.maxMilliSecondsBetweenCannonFires - 100 : 
				previousLevel.maxMilliSecondsBetweenCannonFires - 500;
		NextLevel.maxGameSpriteSpeed = previousLevel.maxGameSpriteSpeed + 1;
		NextLevel.minGameSpriteSpeed = previousLevel.minGameSpriteSpeed + 1;
		NextLevel.maxPlayerSpriteSpeed = doubleIncrementEnemy == true ?
				previousLevel.maxPlayerSpriteSpeed + 2 : previousLevel.maxPlayerSpriteSpeed + 1;
		NextLevel.minPlayerSpriteSpeed = doubleIncrementEnemy == true ?
				previousLevel.minPlayerSpriteSpeed + 2 : previousLevel.minPlayerSpriteSpeed + 1;
		NextLevel.beginningPlayerSpriteSpeed = doubleIncrementEnemy == true ?
				previousLevel.beginningPlayerSpriteSpeed + 2 : previousLevel.beginningPlayerSpriteSpeed +1;
		NextLevel.maxRadius = previousLevel.maxRadius + 2;
		NextLevel.minRadius = previousLevel.minRadius +2;
		NextLevel.playerBeginningRadius = previousLevel.playerBeginningRadius;
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
	public int getProjectileSpeed() {
		return this.projectileSpeed;
	}

	@Override
	public int getMaxMilliSecondsBetweenFires() {
		return this.maxMilliSecondsBetweenCannonFires;
	}
}
