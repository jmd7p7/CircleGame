package sprites;
import java.util.TimerTask;

import Positioning.Coordinate;
import UI.ICannonFireHandler;
import UI.ICollisionHandler;
import paths.PathProvider;

public class CannonSprite extends GameSprite {	
	private PathProvider pathProvider;
	private ICannonFireHandler cannonFireHandler;
	private int maxMillisecondsBetweenCannonFire;
	private long timeAtLastCannonFire;
	
	public CannonSprite(Coordinate coord, int radius, int speed, PathProvider pathProvider, 
			ICannonFireHandler cannonFireHandler, int maxMilliSecondsBetweenCannonFire) {
		
		super(coord, radius, speed, java.awt.Color.BLACK);
		this.maxMillisecondsBetweenCannonFire = maxMilliSecondsBetweenCannonFire;
		this.timeAtLastCannonFire = System.currentTimeMillis();
		this.pathProvider = pathProvider;
		this.setCoordiante(pathProvider.getNextCoordinate(-(this.getRadius())));
		this.cannonFireHandler = cannonFireHandler;
	}
	
	@Override
	public void updatePosition() {
		setCoordiante(this.pathProvider.getNextCoordinate(this.getX() + this.getSpeed()));
		if(System.currentTimeMillis() - this.timeAtLastCannonFire > this.maxMillisecondsBetweenCannonFire){
			this.cannonFireHandler.FireCannon(this.getCoordinate());
			this.timeAtLastCannonFire = System.currentTimeMillis();
		}
	}

	@Override
	public void handleCollisionWithPlayer(ICollisionHandler handler) {
		handler.HandleCollisionSound("Enemy");
	}

	
}
