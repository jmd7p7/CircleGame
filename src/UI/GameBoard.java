package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import GamePlay.PlayerLoseHandler;
import GamePlay.PlayerWinHandler;
import GamePlay.SpriteManager;
import Level.Level;
import Sprite.Sprite;

class GameBoard extends JPanel {
	private SpriteManager spriteManager;
	private boolean gameRunning = true;
	private static final int ONE_BILLION = 1000000000;
	private static final int ONE_MILLION = 1000000;
	public static final Color CANVAS_BACKGROUND = Color.WHITE;

	public GameBoard(Level level, PlayerWinHandler winHandler, PlayerLoseHandler lossHandler, 
			         IScreenDimensionsProvider dimensionsProvider, IScoreUpdater scoreUpdater){
		this.setPreferredSize(new Dimension(dimensionsProvider.getGameBoardWidth(), dimensionsProvider.getHeight()));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY));
		this.spriteManager = new SpriteManager(dimensionsProvider.getGameBoardWidth(), dimensionsProvider.getHeight(), 
				winHandler, lossHandler, Level.getNextLevel(level), scoreUpdater);
	}
	
	public GameBoard(PlayerWinHandler winHandler, PlayerLoseHandler lossHandler, 
			IScreenDimensionsProvider dimensionsProvider, IScoreUpdater scoreUpdater){
		this.setPreferredSize(new Dimension(dimensionsProvider.getGameBoardWidth(), dimensionsProvider.getHeight()));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY));
		this.spriteManager = new SpriteManager(dimensionsProvider.getGameBoardWidth(), dimensionsProvider.getHeight(), 
				winHandler, lossHandler, Level.getLevelOne(), scoreUpdater);
	}
	
	public Level getLevel(){
		return spriteManager.getLevel();
	}
	
	public int getLevelAsNumber(){
		return this.spriteManager.getLevelAsNumber();
	}
	
	public void updatePlayerPosition(KeyEvent evt){
		this.spriteManager.updatePlayerPosition(evt);
	}
	
	public void gameLoop() {
		long nanosecondAccumulator = 0;
		long fps = 0;
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 40;
		final long OPTIMAL_NANOSECONDS_EACH_FRAME = ONE_BILLION / TARGET_FPS;

		while (gameRunning) {
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) OPTIMAL_NANOSECONDS_EACH_FRAME);

			// The following isn't necessary. Can be commented out. Only used to calculate fps
			nanosecondAccumulator = nanosecondAccumulator +  updateLength;
			fps = fps + 1;

			// update our FPS counter if a second has passed since we last recorded
			if (nanosecondAccumulator >= ONE_BILLION) {
				nanosecondAccumulator = 0;
				fps = 0;
			}

			updateSpritePositions(delta);
			redrawSpritesUpdates();

			try {
				long nanosecondsThisFrame = System.nanoTime() - lastLoopTime;  
				long nanosecondsToSleepToGetDesiredFrameRate =
						OPTIMAL_NANOSECONDS_EACH_FRAME - nanosecondsThisFrame;
				long millisecondsToSleep = nanosecondsToSleepToGetDesiredFrameRate / ONE_MILLION;

				//System.out.println("Milliseconds to sleep " + nanosecondsToSleepToGetDesiredFrameRate / ONE_MILLION);
				Thread.sleep(millisecondsToSleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void redrawSpritesUpdates() {
		repaint();
	}

	private void updateSpritePositions(double delta) {
		spriteManager.updatePositions(delta);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(CANVAS_BACKGROUND);
		
		for(Sprite sprite : spriteManager.getAllSprites()){
			g.setColor(sprite.getColor());
			g.fillOval(sprite.getX_Coord(), sprite.getY_Coord(), sprite.getRadius()*2, sprite.getRadius()*2);
		}
	}
}
