package Sprite;
import Positioning.Coordinate;

public abstract class Sprite {
	private final int radius;
	private final java.awt.Color color;	
	private Coordinate coordinate;
	
	public Sprite(int radius,java.awt.Color color, int startingY_coord){
		this.radius = radius;
		this.color = color;
		this.coordinate = new Coordinate(0 - radius*2, startingY_coord);
	}
	
	public Sprite(int radius,java.awt.Color color, int startingX_coord, int startingY_coord){
		this.radius = radius;
		this.color = color;		
		this.coordinate = new Coordinate(startingX_coord, startingY_coord);
	}
	
	public void setCoordinate(Coordinate newCoordinate){
		this.coordinate = newCoordinate;
	}
	
	public int getRadius(){
		return this.radius;
	}
	public java.awt.Color getColor(){
		return this.color;
	}

	public int getX_Coord(){
		return this.coordinate.getX();
	}
	
	public int getY_Coord(){
		return this.coordinate.getY();
	}
}
