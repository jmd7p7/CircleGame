package Tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.Assert;
import GamePlay.DifficultyLevel;
import GamePlay.SpriteManager;
import Sprite.GameSprite;
import Sprite.PlayerSprite;
import Sprite.WinLoseSprite;
import Sprite.WinSprite;

import org.junit.Test;

public class SpriteManagerTests {
	SpriteManager spriteManager = new SpriteManager(DifficultyLevel.EASY, 400, 400, ()-> {}, ()->{});
	
/*	@Test
	public void testCheckForCollisionBetweenEdibleOrEnemySpriteAndPanel_CollisionOnTop() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//arrange
		GameSprite[] sprites = {new WinSprite(20, 20, 10, 400)};
		int screenHeight = 400;
		Class targetClass = spriteManager.getClass();
		Class[] argTypes = new Class[] {GameSprite.class, int.class};
		Method checkForCollisionsMethod = targetClass.getDeclaredMethod("checkForCollisionBetweenEdibleOrEnemySpriteAndPanel", argTypes);
		checkForCollisionsMethod.setAccessible(true);
	    Object[] parms = new Object[] {sprites[0], 0};
	    
		//act
	    checkForCollisionsMethod.invoke(spriteManager, parms);
	    
	    //assert
	    assertEquals(399, sprites[0].getY_Coord());
	}*/

}
