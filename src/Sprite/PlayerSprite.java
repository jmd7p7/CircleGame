package Sprite;

import java.awt.Color;

public class PlayerSprite extends Sprite{
		private static final int PLAYER_BEGINNING_SPEED = 3;
		private static final int PLAYER_BEGINNING_RADIUS = 10;
		private static final int MAX_RADIUS = 50;
		private static final int MIN_RADIUS = 5;
		private static final int MIN_VERTICAL_SPEED = 1;
		private static final int MAX_VERTICAL_SPEED = 30;
		private final int verticalSpeed;
		
	public PlayerSprite(int startingX_coord, int startingY_coord) {
		super(PLAYER_BEGINNING_RADIUS,Color.CYAN, startingX_coord, startingY_coord);
		this.verticalSpeed = PLAYER_BEGINNING_SPEED;
	}
	
	public PlayerSprite(PlayerSprite oldSelf, EdibleSprite edibleSprite){
		super(oldSelf, edibleSprite, MAX_RADIUS);
		
		int potentialNewSpeed = oldSelf.verticalSpeed + edibleSprite.getSpeedIncreaseReward();
		this.verticalSpeed = potentialNewSpeed <= MAX_VERTICAL_SPEED ? potentialNewSpeed : MAX_VERTICAL_SPEED;
	}
	
	public PlayerSprite(PlayerSprite oldSelf, EnemySprite enemySprite){
		super(oldSelf, enemySprite, MIN_RADIUS);
		
		int potentialNewSpeed = oldSelf.verticalSpeed - enemySprite.getSpeedDecreasePenalty();
		this.verticalSpeed = potentialNewSpeed >= MIN_VERTICAL_SPEED ? potentialNewSpeed : MIN_VERTICAL_SPEED;
	}

	public int getVerticalSpeed(){
		return this.verticalSpeed;
	}
}
