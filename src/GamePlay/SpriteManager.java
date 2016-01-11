package GamePlay;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import Sprite.EdibleSprite;
import Sprite.EnemySprite;
import Sprite.GameSpriteType;
import Sprite.WinLoseSprite;
import Sprite.GameSprite;
import Sprite.PlayerSprite;
import Utility.Utility;

public class SpriteManager {
	private final int max_enemies;
	private final int max_edibles;
	private GameSprite[] GameSprites;
	private ArrayList<WinLoseSprite> WinLoseSprites;
	private PlayerSprite playerSprite;
	private final int screenWidth;
	private final int screenHeight;
	PlayerWinHandler winHandler;
	PlayerLoseHandler lossHandler;
	
	public SpriteManager(DifficultyLevel difficultyLevel, int screenWidth, int screenHeight, 
			PlayerWinHandler winHandler, PlayerLoseHandler lossHandler){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.winHandler = winHandler;
		this.lossHandler = lossHandler;
		this.playerSprite = new PlayerSprite(screenWidth - 100, screenHeight/2);
		WinLoseSprites = new ArrayList<WinLoseSprite>();
		
		switch(difficultyLevel){
			case EASY:
				max_enemies = 1;
				max_edibles = 2;
				createInitialSpriteArray(1, 2);
				break;
			case MODERATE:
				max_enemies = 3;
				max_edibles = 4;
				createInitialSpriteArray(3, 4);
				break;
			case DIFFICULT:
				max_enemies = 5;
				max_edibles = 5;
				createInitialSpriteArray(5, 5);
				break;
			default:
				max_enemies = 1;
				max_edibles = 2;
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
	
	public PlayerSprite getPlayerSprite(){
		return this.playerSprite;
	}
	
	public void movePlayerPosition(KeyEvent evt){
		switch (evt.getKeyCode()) {
		case KeyEvent.VK_UP:
			playerSprite.setY_Coord(playerSprite.getY_Coord() - playerSprite.getVerticalSpeed());
			break;
		case KeyEvent.VK_DOWN:
			playerSprite.setY_Coord(playerSprite.getY_Coord() + playerSprite.getVerticalSpeed());
			break;
		}
	}
	
	public void updatePositions(Double delta){
		for(int i = 0; i < GameSprites.length; i++){
			GameSprites[i].updatePosition(delta);
			checkForCollisionBetweenPlayerAndGameSprites(GameSprites[i], i);
			checkForCollisionBetweenEdibleOrEnemySpriteAndPanel(GameSprites[i], i);
			checkForOffScreen(GameSprites[i], i);
		}
		for(int i = 0; i < WinLoseSprites.size(); i++){	
			WinLoseSprites.get(i).updatePosition(delta);
			checkForCollisionBetweenPlayerAndGameSprites(WinLoseSprites.get(i), i);
			checkForCollisionBetweenWinLoseSpriteAndPanel(WinLoseSprites.get(i), i);
			checkForOffScreen(WinLoseSprites.get(i), i);
		}
		if(WinLoseSprites.size() == 0 && System.currentTimeMillis() % 73 ==  0){
			SpriteCreator creator = SpriteCreator.getInstance();
			WinLoseSprites.add((WinLoseSprite)creator.createNewGameSprite(GameSpriteType.WINLOSE, screenHeight));
		}
	}


	private void checkForCollisionBetweenWinLoseSpriteAndPanel(
			WinLoseSprite winLoseSprite, int i) {
		if(CollisionDetectionManager.detectCollisionBetweenSpriteAndTopOfPanel(winLoseSprite)){
			WinLoseSprites.get(i).setY_Coord(1); 
		}
		else if(CollisionDetectionManager.detectCollisionBetweenSpriteAndBottomOfPanel(winLoseSprite, screenHeight)){
			WinLoseSprites.get(i).setY_Coord(screenHeight -(WinLoseSprites.get(i).getRadius()*2 + 1)); 
		}
		
	}

	private void checkForCollisionBetweenEdibleOrEnemySpriteAndPanel(GameSprite gameSprite, int i) {
		if(CollisionDetectionManager.detectCollisionBetweenSpriteAndTopOfPanel(gameSprite)){
			GameSprites[i].setY_Coord(1); 
		}
		else if(CollisionDetectionManager.detectCollisionBetweenSpriteAndBottomOfPanel(gameSprite, screenHeight)){
			GameSprites[i].setY_Coord(screenHeight - (GameSprites[i].getRadius()*2 + 1)); 
		}
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
			case WINLOSE:
				WinLoseSprites.remove(index);
				break;
			}
		}
		
	}

	private void checkForCollisionBetweenPlayerAndGameSprites(GameSprite gameSprite, int index) {
		if(CollisionDetectionManager.detectCollisionBetweenTwoSprites(playerSprite, gameSprite)){
			handleCollision(gameSprite, index);
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
			if(winLoseSpriate.PlayerWins() == true){
				winHandler.handleWin();
			}
			else{
				lossHandler.handlePlayerLoss();
			}
			break;
		}
	}
}
