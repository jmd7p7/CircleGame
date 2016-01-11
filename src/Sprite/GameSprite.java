package Sprite;

import java.awt.Color;
import java.util.Random;

import GamePlay.DirectionChangeType;
import GamePlay.GameSpriteDirectionRandomizer;

public abstract class GameSprite extends Sprite{
	private final int horizontalSpeed;
	private final int verticalSpeed;
	private final GameSpriteType type;
	private static Random ran = new Random(System.currentTimeMillis());
	private GameSpriteDirectionRandomizer randomizer;

	
	public GameSprite(int radius, Color color, int horizontalSpeed, int verticalSpeed, 
			          GameSpriteType type, int startingY_coord) {
		super(radius, color, startingY_coord);
		this.verticalSpeed = verticalSpeed;
		this.horizontalSpeed = horizontalSpeed;
		this.type = type;
		this.randomizer = new GameSpriteDirectionRandomizer();
	}

	public void updatePosition(Double delta){
		this.x_coord += (int) (delta * horizontalSpeed);
		switch(randomizer.getDirectionChange()){
		case UP:
			this.y_coord -= verticalSpeed;
			break;
		case DOWN:
			this.y_coord += verticalSpeed;
			break;
		case NONE:
			break;
		}
		
	}
	
	public GameSpriteType getGameSpriteType(){
		return this.type;
	}
}
