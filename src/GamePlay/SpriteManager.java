package GamePlay;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import Level.Level;
import Sprite.*;
import UI.IScoreUpdater;
import Positioning.Coordinate;

public class SpriteManager {
	private GameSprite[] GameSprites;
	private ArrayList<WinLoseSprite> WinLoseSprites;
	private ArrayList<CannonSprite> CannonSprites;
	private ArrayList<ProjectileSprite> projectiles;
	private PlayerSprite playerSprite;
	private final int gameBoardWidth;
	private final int gameBoardHeight;
	private static final int CANNON_SPRITE_MODULUS = 857;
	private static final int WINLOSE_SPRITE_MODULUS = 139;
	private static final Boolean PLAYER_WINS = true; 
	private static final int INCUBATION_PERIOD_IN_MILLIS = 200;
	private static final int INCUBATION_PERIOD_ZER0_MILLIS = 0;
	PlayerWinHandler winHandler;
	PlayerLoseHandler lossHandler;
	private Level level;
	SpriteCreator spriteCreator;
	IScoreUpdater scoreUpdater;
	
	public SpriteManager(int gameBoardWidth, int gameBoardHeight, PlayerWinHandler winHandler, 
			PlayerLoseHandler lossHandler, Level level, IScoreUpdater scoreUpdater){
		this.spriteCreator = new SpriteCreator();
		this.level = level;
		this.gameBoardWidth = gameBoardWidth;
		this.gameBoardHeight = gameBoardHeight;
		this.winHandler = winHandler;
		this.lossHandler = lossHandler;
		this.playerSprite = new PlayerSprite(gameBoardWidth - 50, gameBoardHeight/2, level);
		WinLoseSprites = new ArrayList<WinLoseSprite>();
		CannonSprites = new ArrayList<CannonSprite>();
		projectiles = new ArrayList<ProjectileSprite>();
		createInitialSpriteArray(level.getNumEnemySprites(), level.getNumEdibleSprites());
		this.scoreUpdater = scoreUpdater;
	}
	
	private void createInitialSpriteArray(int numEnemySprites, int numEdibleSprites) {
		GameSprites = new GameSprite[numEnemySprites + numEdibleSprites];
		for(int i = 0; i < numEnemySprites; i++)
			GameSprites[i] = spriteCreator.createNewGameSprite(GameSpriteType.ENEMY, gameBoardHeight, 
					level, INCUBATION_PERIOD_IN_MILLIS*i);
		for(int i = 0; i < numEdibleSprites; i++){
			GameSprites[i + numEnemySprites] = spriteCreator.createNewGameSprite(GameSpriteType.EDIBLE, 
					gameBoardHeight, level, INCUBATION_PERIOD_IN_MILLIS*i);
		}
	}
		
	public Level getLevel(){
		return this.level;
	}
	
	public int getLevelAsNumber(){
		return this.level.getLevel();
	}
	
	public void updatePlayerPosition(KeyEvent evt){
		int newY_coord;
		switch (evt.getKeyCode()) {
			case KeyEvent.VK_UP:
				newY_coord = playerSprite.getY_Coord() - playerSprite.getVerticalSpeed();
				break;
			case KeyEvent.VK_DOWN:
				newY_coord = playerSprite.getY_Coord() + playerSprite.getVerticalSpeed();
				break;
			default:
				return;
		}
		playerSprite.setCoordinate(new Coordinate(playerSprite.getX_Coord(), newY_coord));
	}
	
	public void updatePositions(Double delta){
		updateGameSpritePositions(delta);
		updateWinLoseSpritePositions(delta);
		if(WinLoseSprites.size() <= 1 && System.currentTimeMillis() % WINLOSE_SPRITE_MODULUS ==  0){
			WinLoseSprites.add((WinLoseSprite)spriteCreator.createNewGameSprite(GameSpriteType.WINLOSE, 
					gameBoardHeight, level, INCUBATION_PERIOD_ZER0_MILLIS));
		}
		updateCannonSpritePositions(delta);
		if(CannonSprites.size() <=1 && System.currentTimeMillis() % CANNON_SPRITE_MODULUS == 0){
			CannonSprites.add((CannonSprite) spriteCreator.createNewGameSprite(GameSpriteType.CANNON, 
					gameBoardHeight, level, INCUBATION_PERIOD_ZER0_MILLIS));
		}
		updateProjectileSpritePositions(delta);
	}

	private void updateCannonSpritePositions(Double delta) {
		for(int i = 0; i < CannonSprites.size(); i++){
			CannonSprites.get(i).updatePosition(delta);
			if(CannonSprites.get(i).FireCannon()){
				projectiles.add(new ProjectileSprite(CannonSprites.get(i), playerSprite, level.getProjectileSpeed()));
			}
			checkForCollisionBetweenGameSpriteAndPanel(CannonSprites.get(i));
			checkForOffScreen(CannonSprites.get(i), i);
		}
	}

	private void updateWinLoseSpritePositions(Double delta) {
		for(int i = 0; i < WinLoseSprites.size(); i++){	
			WinLoseSprites.get(i).updatePosition(delta);
			if(CollisionDetectionManager.detectCollisionBetweenTwoSprites(WinLoseSprites.get(i), playerSprite)){
				handleCollision(WinLoseSprites.get(i), i);
			}
			checkForCollisionBetweenGameSpriteAndPanel(WinLoseSprites.get(i));
			checkForOffScreen(WinLoseSprites.get(i), i);
		}
	}

	private void updateGameSpritePositions(Double delta) {
		for(int i = 0; i < GameSprites.length; i++){
			GameSprites[i].updatePosition(delta);
			if(CollisionDetectionManager.detectCollisionBetweenTwoSprites(GameSprites[i], playerSprite)){
				handleCollision(GameSprites[i], i);
			}
			checkForCollisionBetweenGameSpriteAndPanel(GameSprites[i]);
			checkForOffScreen(GameSprites[i], i);
		}
	}
	
	public void updateProjectileSpritePositions(Double delta){
		ArrayList<ProjectileSprite> removeList = new ArrayList<ProjectileSprite>();
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).updatePosition(delta);
			if(CollisionDetectionManager.detectCollisionBetweenTwoSprites(projectiles.get(i), playerSprite)){
				lossHandler.handlePlayerLoss();
			}
			if(CollisionDetectionManager.detectCollisionBetweenProjectileAndPanel(projectiles.get(i), 
					gameBoardWidth, gameBoardHeight)){
				removeList.add(projectiles.get(i));
			}
		}
		for(ProjectileSprite p : removeList){
			projectiles.remove(p);
		}
		System.out.println(projectiles.size() + " projectiles");
	}

	private void checkForCollisionBetweenGameSpriteAndPanel(GameSprite gameSprite) {
		int newY_coord = gameSprite.getY_Coord();
		if(CollisionDetectionManager.detectCollisionBetweenSpriteAndTopOfPanel(gameSprite)){
			newY_coord = 1;
			gameSprite.changeDirectoin();
		}
		else if(CollisionDetectionManager.detectCollisionBetweenSpriteAndBottomOfPanel(gameSprite, gameBoardHeight)){
			newY_coord = gameBoardHeight -(gameSprite.getRadius()*2 + 1);
			gameSprite.changeDirectoin();
		}
		else{
			return;
		}
		gameSprite.setCoordinate(new Coordinate(gameSprite.getX_Coord(), newY_coord));
	}

	private void checkForOffScreen(GameSprite gameSprite, int index) {
		if(gameSprite.getX_Coord() >= gameBoardWidth){
			switch(gameSprite.getGameSpriteType()){
			case EDIBLE:
				GameSprites[index] = spriteCreator.createNewGameSprite(GameSpriteType.EDIBLE, 
						gameBoardHeight, level, INCUBATION_PERIOD_ZER0_MILLIS);
				break;
			case ENEMY:
				GameSprites[index] = spriteCreator.createNewGameSprite(GameSpriteType.ENEMY, 
						gameBoardHeight, level, INCUBATION_PERIOD_ZER0_MILLIS);
				break;
			case CANNON:
				CannonSprites.remove(index);
				break;
			case WINLOSE:
				WinLoseSprites.remove(index);
				break;
			}
		}	
	}

	private void handleCollision(GameSprite gameSprite, int index) {
		switch(gameSprite.getGameSpriteType()){
		case EDIBLE:
			EdibleSprite edibleSprite = (EdibleSprite) gameSprite;
			this.playerSprite = spriteCreator.enhancePlayerSprite(playerSprite, edibleSprite, level);
			GameSprites[index] = spriteCreator.createNewGameSprite(GameSpriteType.EDIBLE, gameBoardHeight, 
					level, INCUBATION_PERIOD_ZER0_MILLIS);
			scoreUpdater.addPointsToScore(edibleSprite.getScoreIncreaseAmount());
			break;
		case ENEMY:
			EnemySprite enemySprite = (EnemySprite) gameSprite;
			this.playerSprite = spriteCreator.diminishPlayerSprite(playerSprite, enemySprite, level);
			GameSprites[index] = spriteCreator.createNewGameSprite(GameSpriteType.ENEMY, gameBoardHeight, 
					level, INCUBATION_PERIOD_ZER0_MILLIS);
			scoreUpdater.removePointsFromScore(enemySprite.getScoreReductionAmount());
			break;
		case WINLOSE:
			WinLoseSprite winLoseSpriate = (WinLoseSprite) gameSprite;
			if(winLoseSpriate.getPlayerWinStatus() == PLAYER_WINS){
				WinSprite winSprite = (WinSprite) gameSprite;
				scoreUpdater.addPointsToScore(winSprite.getScoreIncreaseAmount());
				winHandler.handleWin();
			}
			else{
				lossHandler.handlePlayerLoss();
			}
			break;
		}
	}

	public ArrayList<Sprite> getAllSprites() {
		ArrayList<Sprite> allSprites = new ArrayList<Sprite>();
		
		for(GameSprite gameSprite : this.GameSprites){
			allSprites.add(gameSprite);
		}
		allSprites.addAll(this.WinLoseSprites);
		allSprites.addAll(CannonSprites);
		allSprites.addAll(this.projectiles);
		allSprites.add(this.playerSprite);
		
		return allSprites;

	}
}
