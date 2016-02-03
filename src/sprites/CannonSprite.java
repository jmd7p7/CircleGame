package sprites;
import java.util.Timer;
import java.util.TimerTask;

import Positioning.Coordinate;
import UI.ICannonFireHandler;
import UI.ICollisionHandler;
import paths.PathProvider;

public class CannonSprite extends GameSprite {	
	private PathProvider pathProvider;
	private ICannonFireHandler cannonFireHandler;
	private Timer timer;
	
	private class CannonTimer extends TimerTask{
		private CannonSprite outerClass;
		public CannonTimer(CannonSprite cannonSprite){
			this.outerClass = cannonSprite;
		}
		
		@Override
		public void run() {
			cannonFireHandler.FireCannon(outerClass.getCoordinate());
		}
	}
	
	public CannonSprite(Coordinate coord, int radius, int speed, PathProvider pathProvider, 
			ICannonFireHandler cannonFireHandler) {
		
		super(coord, radius, speed, java.awt.Color.BLACK);
		
		this.pathProvider = pathProvider;
		this.cannonFireHandler = cannonFireHandler;
		this.timer = new Timer();
		timer.scheduleAtFixedRate(new CannonTimer(this), 50, 1000);
	}
	
	@Override
	public void updatePosition() {
		setCoordiante(this.pathProvider.getNextCoordinate(this.getX() + this.getSpeed()));
	}

	@Override
	public void handleCollisionWithPlayer(ICollisionHandler handler) {
		handler.HandleCollisionSound("Enemy");
	}
	
}
