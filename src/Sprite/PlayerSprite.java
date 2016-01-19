package Sprite;

import java.awt.Color;

import Level.LevelPlayerSpriteInformation;

public class PlayerSprite extends Sprite{
		private final int speed;
		
	public PlayerSprite(int startingX_coord, int startingY_coord, LevelPlayerSpriteInformation playerSpriteInformation) {
		super(playerSpriteInformation.getPlayerBeginningRadius(),Color.CYAN, startingX_coord, startingY_coord);
		this.speed = playerSpriteInformation.getPlayerBeginningSpeed();
	}
	
	public PlayerSprite(int radius, int X_coord, int Y_coord, int speed){
		super(radius, java.awt.Color.CYAN, X_coord, Y_coord);
		this.speed = speed;
	}
	
	public int getVerticalSpeed(){
		return this.speed;
	}
}
