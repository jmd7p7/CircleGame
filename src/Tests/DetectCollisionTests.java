/*package Tests;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import junit.framework.Assert;

import org.junit.Test;

import GamePlay.CollisionDetectionManager;
import Sprite.GetBiggerEdibleSprite;
import Sprite.PlayerSprite;
import Sprite.Sprite;

public class DetectCollisionTests {
	@Test
	public void collisionBewteenTwoSpritesShouldBeDetectedTest() {
		//arrange
		PlayerSprite sprite1 = new PlayerSprite(0, 1);
		PlayerSprite sprite2 = new PlayerSprite(0, 20);
	
		//act
		Boolean result = 
				CollisionDetectionManager.detectCollisionBetweenTwoSprites(sprite1, sprite2);
	
		//assert
		assertTrue(result);

	}
	
	@Test
	public void collisionBetweenTwoSpritesShouldNotBeDetectedTest() {
		//arrange
		PlayerSprite sprite1 = new PlayerSprite(-10, 10);
		PlayerSprite sprite2 = new PlayerSprite(10, 30);
	
		//act
		Boolean result = 
				CollisionDetectionManager.detectCollisionBetweenTwoSprites(sprite1, sprite2);
	
		//assert
		assertFalse(result);
	}
	
	@Test
	public void collisionBetweenSpriteAndTopPanelShouldBeDetected(){
		//arrange
		PlayerSprite sprite1 = new PlayerSprite(300, 400);
		
		//act
		Boolean result = 
				CollisionDetectionManager.detectCollisionBetweenSpriteAndTopOfPanel(sprite1);
		
		assertTrue(result);
	}
	
	@Test
	public void collisionBetweenSpriteAndTopPanelShouldNotBeDetected(){
		//arrange
		PlayerSprite sprite1 = new PlayerSprite(300, 399);
		int screenHeight = 400;
		//act
		Boolean result = 
				CollisionDetectionManager.detectCollisionBetweenSpriteAndTopOfPanel(sprite1);
		
		assertFalse(result);
	}

	@Test
	public void collisionBetweenSpriteAndBottomPanelShouldBeDetected(){
		//arrange (default height of PlayerSprite is 20)
		PlayerSprite sprite1 = new PlayerSprite(300, 19);
		int screenHeight = 400;
		//act
		Boolean result = 
				CollisionDetectionManager.detectCollisionBetweenSpriteAndBottomOfPanel(sprite1, screenHeight);
		
		assertTrue(result);
	}
	
	@Test
	public void collisionBetweenSpriteAndBottomPanelShouldNotBeDetected(){
		//arrange (default height of PlayerSprite is 20)
		PlayerSprite sprite1 = new PlayerSprite(300, 21);
		int screenHeight = 400;
		
		//act
		Boolean result = 
				CollisionDetectionManager.detectCollisionBetweenSpriteAndBottomOfPanel(sprite1, screenHeight);
		
		assertFalse(result);
	}
}
*/