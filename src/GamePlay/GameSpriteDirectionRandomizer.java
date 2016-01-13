package GamePlay;

import java.util.Random;

public class GameSpriteDirectionRandomizer {
	private static Random ran = new Random(System.currentTimeMillis());
	private DirectionChangeType directionChangeType;
	private long timeAtDirectionChange;
	private int millisecondsBetweenDirectionChange;
	private static final int ONE_MILLION = 1000000;

	
	public GameSpriteDirectionRandomizer(){
		timeAtDirectionChange = System.nanoTime();
		millisecondsBetweenDirectionChange = setRandomMilliseondsBetweenDirectionChange();
		setDirectionChangeType();
	}
	
	private int setRandomMilliseondsBetweenDirectionChange() {
		return ran.nextInt(1500) + 250;
	}

	public DirectionChangeType getDirectionChange(){
		if((System.nanoTime() - timeAtDirectionChange)/ONE_MILLION > millisecondsBetweenDirectionChange){
			setDirectionChangeType();
			timeAtDirectionChange = System.nanoTime();
			return this.directionChangeType;
		}
		return this.directionChangeType;
	}
	
	public void setDirectionChangeType(){
		switch(ran.nextInt(3)){
		case 0:
			directionChangeType = DirectionChangeType.UP;
			break;
		case 1:
			directionChangeType = DirectionChangeType.DOWN;
			break;
		case 2:
			directionChangeType = DirectionChangeType.NONE;
			break;
		}
	}
}
