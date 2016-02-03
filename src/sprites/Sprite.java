package sprites;
import Positioning.Coordinate;

public abstract class Sprite {
	private Coordinate coordinate;
	private int radius;
	private int speed;
	private java.awt.Color color;
	
	public Sprite(Coordinate coord, int radius, int speed, java.awt.Color color){
		this.coordinate = coord;
		this.radius = radius;
		this.speed = speed;
		this.color = color;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public java.awt.Color getColor(){
		return this.color;
	}
	
	public int getX(){
		return this.coordinate.getX();
	}
	
	public int getY(){
		return this.coordinate.getY();
	}
	
	public void setCoordiante(Coordinate newCoord){
		this.coordinate = newCoord;
	}
	
	public int getRadius(){
		return this.radius;
	}
	
	public Coordinate getCoordinate(){
		return this.coordinate;
	}
	
}
