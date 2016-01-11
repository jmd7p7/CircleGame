package Sprite;

public abstract class Sprite {
	private final int radius;
	private final java.awt.Color color;	
	protected int x_coord;
	protected int y_coord;
	
	public Sprite(int radius,java.awt.Color color, int startingY_coord){
		this.radius = radius;
		this.color = color;
		this.y_coord = startingY_coord;
		this.x_coord = 0 - radius*2;
	}
	
	public Sprite(int radius,java.awt.Color color, int startingX_coord, int startingY_coord){
		this.radius = radius;
		this.color = color;
		this.y_coord = startingY_coord;
		this.x_coord = startingX_coord;
	}

	
	public Sprite(PlayerSprite oldPlayer, EdibleSprite edibleSprite, int maxRadius){
		int potentialNewRadius = oldPlayer.getRadius() + edibleSprite.getSizeIncreaseReward();
		this.radius = potentialNewRadius <= maxRadius ? potentialNewRadius : maxRadius;
		this.color = oldPlayer.getColor();
		this.x_coord = oldPlayer.x_coord;
		this.y_coord = oldPlayer.y_coord;
	}
	
	public Sprite(PlayerSprite oldPlayer, EnemySprite enemySprite, int minRadius){
		int potentialNewRadius = oldPlayer.getRadius() - enemySprite.getSizeDecreasePenalty();
		this.radius = potentialNewRadius >= minRadius ? potentialNewRadius : minRadius;
		this.color = oldPlayer.getColor();
		this.x_coord = oldPlayer.x_coord;
		this.y_coord = oldPlayer.y_coord;
	}
	
	public void setX_Coord(int newCoord){
		this.x_coord = newCoord;
	}
	public void setY_Coord(int newCoord){
		this.y_coord = newCoord;
	}
	
	public int getRadius(){
		return this.radius;
	}
	public java.awt.Color getColor(){
		return this.color;
	}

	public int getX_Coord(){
		return this.x_coord;
	}
	
	public int getY_Coord(){
		return this.y_coord;
	}
}
