package GamePlay;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import Sprite.*;
import Positioning.Coordinate;

public class SpriteManager {
	private GameSprite[] GameSprites;
	private ArrayList<WinLoseSprite> WinLoseSprites;
	private ArrayList<CannonSprite> CannonSprites;
	private ArrayList<Projectile> projectiles;
	private PlayerSprite playerSprite;
	private final int screenWidth;
	private final int screenHeight;
	PlayerWinHandler winHandler;
	PlayerLoseHandler lossHandler;
	private final int projectileSpeed;
	
	public SpriteManager(DifficultyLevel difficultyLevel, int screenWidth, int screenHeight, 
			PlayerWinHandler winHandler, PlayerLoseHandler lossHandler){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.winHandler = winHandler;
		this.lossHandler = lossHandler;
		this.playerSprite = new PlayerSprite(screenWidth - 100, screenHeight/2);
		WinLoseSprites = new ArrayList<WinLoseSprite>();
		CannonSprites = new ArrayList<CannonSprite>();
		projectiles = new ArrayList<Projectile>();
		this.projectileSpeed = 4;
		
		switch(difficultyLevel){
			case EASY:
				createInitialSpriteArray(1, 2);
				break;
			case MODERATE:
				createInitialSpriteArray(3, 4);
				break;
			case DIFFICULT:
				createInitialSpriteArray(5, 5);
				break;
			default:
				break;
		}
	}
	
	private void createInitialSpriteArray(int numEnemySprites, int numEdibleSprites) {
		SpriteCreator creator = SpriteCreator.getInstance();
		GameSprites = new GameSprite[numEnemySprites + numEdibleSprites];
		for(int i = 0; i < numEnemySprites; i++)
			GameSprites[i] = creator.createNewGameSprite(GameSpriteType.ENEMY, screenHeight);
		for(int i = 0; i < numEdibleSprites; i++){
			GameSprites[i + numEnemySprites] = creator.createNewGameSprite(GameSpriteType.EDIBLE, screenHeight);
		}
	}

	public  GameSprite[] getGameSprites(){
		return Arrays.copyOf(GameSprites, GameSprites.length);
	}
	
	public ArrayList<WinLoseSprite> getWinLoseSprites(){
		return this.WinLoseSprites;
	}
	
	public ArrayList<CannonSprite> getCannonSprites(){
		return this.CannonSprites;
	}
	
	public ArrayList<Projectile> getProjectiles(){
		return this.projectiles;
	}
	
	public PlayerSprite getPlayerSprite(){
		return this.playerSprite;
	}
	
	public void movePlayerPosition(KeyEvent evt){
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
		for(int i = 0; i < GameSprites.length; i++){
			GameSprites[i].updatePosition(delta);
			if(CollisionDetectionManager.detectCollisionBetweenTwoSprites(GameSprites[i], playerSprite)){
				handleCollision(GameSprites[i], i);
			}
			checkForCollisionBetweenGameSpriteAndPanel(GameSprites[i]);
			checkForOffScreen(GameSprites[i], i);
		}
		for(int i = 0; i < WinLoseSprites.size(); i++){	
			WinLoseSprites.get(i).updatePosition(delta);
			if(CollisionDetectionManager.detectCollisionBetweenTwoSprites(WinLoseSprites.get(i), playerSprite)){
				handleCollision(WinLoseSprites.get(i), i);
			}
			checkForCollisionBetweenGameSpriteAndPanel(WinLoseSprites.get(i));
			checkForOffScreen(WinLoseSprites.get(i), i);
		}
		if(WinLoseSprites.size() <= 1 && System.currentTimeMillis() % 73 ==  0){
			SpriteCreator creator = SpriteCreator.getInstance();
			WinLoseSprites.add((WinLoseSprite)creator.createNewGameSprite(GameSpriteType.WINLOSE, screenHeight));
		}
		for(int i = 0; i < CannonSprites.size(); i++){
			CannonSprites.get(i).updatePosition(delta);
			if(CannonSprites.get(i).FireCannon()){
				projectiles.add(new Projectile(CannonSprites.get(i), playerSprite, projectileSpeed));
			}
			checkForCollisionBetweenGameSpriteAndPanel(CannonSprites.get(i));
			checkForOffScreen(CannonSprites.get(i), i);
		}
		if(CannonSprites.size() <=1 && System.currentTimeMillis() % 213 == 0){
			SpriteCreator creator = SpriteCreator.getInstance();
			CannonSprites.add((CannonSprite) creator.createNewGameSprite(GameSpriteType.CANNON, screenHeight));
		}
	}
	
	public void updateProjectiles(Double delta){
		ArrayList<Projectile> removeList = new ArrayList<Projectile>();
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).updatePosition(delta);
			if(CollisionDetectionManager.detectCollisionBetweenProjectileAndSprite(projectiles.get(i), playerSprite)){
				lossHandler.handlePlayerLoss();
			}
			if(CollisionDetectionManager.detectCollisionBetweenProjectileAndPanel(projectiles.get(i), 600)){
				removeList.add(projectiles.get(i));
			}
		}
		for(Projectile p : removeList){
			projectiles.remove(p);
		}
	}

	private void checkForCollisionBetweenGameSpriteAndPanel(GameSprite gameSprite) {
		int newY_coord = gameSprite.getY_Coord();
		if(CollisionDetectionManager.detectCollisionBetweenSpriteAndTopOfPanel(gameSprite)){
			newY_coord = 1;
		}
		else if(CollisionDetectionManager.detectCollisionBetweenSpriteAndBottomOfPanel(gameSprite, screenHeight)){
			newY_coord = screenHeight -(gameSprite.getRadius()*2 + 1);
		}
		gameSprite.setCoordinate(new Coordinate(gameSprite.getX_Coord(), newY_coord));
	}

	private void checkForOffScreen(GameSprite gameSprite, int index) {
		if(gameSprite.getX_Coord() >= screenWidth){
			SpriteCreator creator = SpriteCreator.getInstance();
			switch(gameSprite.getGameSpriteType()){
			case EDIBLE:
				GameSprites[index] = creator.createNewGameSprite(GameSpriteType.EDIBLE, screenHeight);
				break;
			case ENEMY:
				GameSprites[index] = creator.createNewGameSprite(GameSpriteType.ENEMY, screenHeight);
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
		SpriteCreator creator = SpriteCreator.getInstance();
		switch(gameSprite.getGameSpriteType()){
		case EDIBLE:
			this.playerSprite = new PlayerSprite(playerSprite, (EdibleSprite)gameSprite);
			GameSprites[index] = creator.createNewGameSprite(GameSpriteType.EDIBLE, screenHeight);
			break;
		case ENEMY:
			this.playerSprite = new PlayerSprite(playerSprite, (EnemySprite)gameSprite);
			GameSprites[index] = creator.createNewGameSprite(GameSpriteType.ENEMY, screenHeight);
			break;
		case WINLOSE:
			WinLoseSprite winLoseSpriate = (WinLoseSprite) gameSprite;
			if(winLoseSpriate.getPlayerWinLoseStatus() == true){
				winHandler.handleWin();
			}
			else{
				lossHandler.handlePlayerLoss();
			}
			break;
		}
	}
}
