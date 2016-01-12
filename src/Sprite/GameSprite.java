package Sprite;

import java.awt.Color;
import Positioning.Coordinate;

import GamePlay.DirectionChangeType;
import GamePlay.GameSpriteDirectionRandomizer;

public abstract class GameSprite extends Sprite{
	private final int horizontalSpeed;
	private final int verticalSpeed;
	private final GameSpriteType type;
	private GameSpriteDirectionRandomizer DirectionRandomizer;

	
	public GameSprite(int radius, Color color, int horizontalSpeed, int verticalSpeed, 
			          GameSpriteType type, int startingY_coord) {
		super(radius, color, startingY_coord);
		this.verticalSpeed = verticalSpeed;
		this.horizontalSpeed = horizontalSpeed;
		this.type = type;
		this.DirectionRandomizer = new GameSpriteDirectionRandomizer();
	}

	public void updatePosition(Double delta){
		int newX_coord = this.getX_Coord() + (int) (delta*horizontalSpeed);
		int newY_coord;
		switch(DirectionRandomizer.getDirectionChange()){
			case UP:
				newY_coord = this.getY_Coord() - verticalSpeed;
				break;
			case DOWN:
				newY_coord = this.getY_Coord() + verticalSpeed;
				break;
			default:
				newY_coord = this.getY_Coord();
		}
		this.setCoordinate(new Coordinate(newX_coord, newY_coord));	
	}
	
	public GameSpriteType getGameSpriteType(){
		return this.type;
	}
}
