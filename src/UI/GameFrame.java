package UI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import GamePlay.DifficultyLevel;
import GamePlay.PlayerLoseHandler;
import GamePlay.PlayerWinHandler;
import GamePlay.SpriteManager;
import Sprite.GameSprite;
import Sprite.WinLoseSprite;

/**
 * Game loop example.
 * Note, this program needs some refactoring.
 */
public class GameFrame extends JFrame implements PlayerWinHandler, PlayerLoseHandler{
	// Constants
	public static final int CANVAS_WIDTH = 600;
	public static final int CANVAS_HEIGHT = 600;
	public static final Color LINE_COLOR = Color.GREEN;
	public static final Color CANVAS_BACKGROUND = Color.WHITE;
	
	SpriteManager spriteManager;

	private DrawCanvas canvas; // the custom drawing canvas (extends JPanel)

	/** Constructor to set up the GUI */
	public GameFrame() {
		spriteManager = new SpriteManager(DifficultyLevel.DIFFICULT, CANVAS_WIDTH, CANVAS_HEIGHT, this, this);
		// Set up a custom drawing JPanel
		canvas = new DrawCanvas();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(canvas, BorderLayout.CENTER);

		// JFrame responds to up and down arrow KeyEvent
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				spriteManager.movePlayerPosition(evt);
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the close button
		setTitle("Game Loop Example");
		requestFocus(); // set the focus to JFrame to receive KeyEvent
	}

	/**
	 * DrawCanvas (inner class) is a JPanel used for custom drawing
	 */
	class DrawCanvas extends JPanel {

		private boolean gameRunning = true;
		private static final int ONE_BILLION = 1000000000;
		private static final int ONE_MILLION = 1000000;

		public void gameLoop() {
			long nanosecondAccumulator = 0;
			long fps = 0;
			
			// nano = billion
			long lastLoopTime = System.nanoTime();
			final int TARGET_FPS = 40;
			// optimal nanoseconds each frame
			final long OPTIMAL_NANOSECONDS_EACH_FRAME = ONE_BILLION / TARGET_FPS;

			// If you plan to update gameRunning
			//   on a separate thread consider declaring
			//   gameRunning as volatile.
			while (gameRunning) {
				// work out how long its been since the last update, this
				// will be used to calculate how far the entities should
				// move this loop
				long now = System.nanoTime();
				long updateLength = now - lastLoopTime;
				lastLoopTime = now;
				double delta = updateLength / ((double) OPTIMAL_NANOSECONDS_EACH_FRAME);

				// The following isn't necessary. Can be commented out.
				// Only used to calculate fps
				nanosecondAccumulator = nanosecondAccumulator +  updateLength;
				fps = fps + 1;

				// update our FPS counter if a second has passed since
				// we last recorded
				if (nanosecondAccumulator >= ONE_BILLION) {
					nanosecondAccumulator = 0;
					fps = 0;
				}
				
				
				// update the game logic
				doGameUpdates(delta);

				// draw frame
				render();

				try {
					long nanosecondsThisFrame = System.nanoTime() - lastLoopTime;  
					long nanosecondsToSleepToGetDesiredFrameRate =
							OPTIMAL_NANOSECONDS_EACH_FRAME - nanosecondsThisFrame;
					// divide by 1M to get milliseconds to sleep
					
					Thread.sleep(nanosecondsToSleepToGetDesiredFrameRate / ONE_MILLION);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		private void render() {
			repaint();
		}

		private void doGameUpdates(double delta) {
			spriteManager.updatePositions(delta);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(CANVAS_BACKGROUND);
			for(GameSprite gameSprite : spriteManager.getGameSprites()){
				g.setColor(gameSprite.getColor());
				g.fillOval(gameSprite.getX_Coord(), gameSprite.getY_Coord(), gameSprite.getRadius()*2, gameSprite.getRadius()*2);
			}
			for(WinLoseSprite WLSprite : spriteManager.getWinLoseSprites()){
				g.setColor(WLSprite.getColor());
				g.fillOval(WLSprite.getX_Coord(), WLSprite.getY_Coord(), WLSprite.getRadius()*2, WLSprite.getRadius()*2);
			}
			g.setColor(spriteManager.getPlayerSprite().getColor());
			g.fillOval(spriteManager.getPlayerSprite().getX_Coord(), spriteManager.getPlayerSprite().getY_Coord(), 
					spriteManager.getPlayerSprite().getRadius()*2, spriteManager.getPlayerSprite().getRadius()*2);
		}
	}

	/** The entry main() method */
	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		game.pack();
		game.setVisible(true);
		game.start();
	}

	private void start() {
		canvas.gameLoop();
	}

	@Override
	public void handleWin() {
		JOptionPane.showMessageDialog(this,
			    "Congratulations!  You've won!");
		System.exit(0);
		
	}

	@Override
	public void handlePlayerLoss() {
		JOptionPane.showMessageDialog(this,
			    "Sorry!  You've lost!");
		System.exit(0);
		
	}
}