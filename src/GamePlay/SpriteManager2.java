package GamePlay;

import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.util.ArrayList;
import Utility.Utility;
import soundeffects.SoundEffectsManager;
import Level.Level;
import sprites.*;
import Positioning.*;
import UI.ICannonFireHandler;
import UI.ICollisionHandler;
import UI.IScoreUpdater;
import UI.IScreenInfoProvider;

public class SpriteManager2 implements ICollisionHandler, ICannonFireHandler {

	private ArrayList<GameSprite> gameSprites;
	private ArrayList<ProjectileSprite> projectilesToAdd;
	private sprites.SpriteCreator spriteCreator;
	private SoundEffectsManager soundEffectsManager;
	private PlayerSprite playerSprite;
	private IScreenInfoProvider screenInfoProvider;
	private IPlayerWinHandler winHandler;
	private IPlayerLoseHandler lossHandler;
	private IScoreUpdater scoreUpdater;
	private Level level;

	private static final int PROJECTILE_RADIUS = 3;

	public SpriteManager2(Level previousLevel, IScreenInfoProvider screenInfoProvider, IPlayerWinHandler winHandler,
			IPlayerLoseHandler lossHandler, IScoreUpdater scoreUpdater) throws MalformedURLException {
		this.level = Level.getNextLevel(previousLevel);
		this.screenInfoProvider = screenInfoProvider;
		this.winHandler = winHandler;
		this.lossHandler = lossHandler;
		this.soundEffectsManager = SoundEffectsManager.getInstance();
		this.scoreUpdater = scoreUpdater;
		this.projectilesToAdd = new ArrayList<ProjectileSprite>();

		// eventually deleted the fully qualified name
		this.spriteCreator = new sprites.SpriteCreator(screenInfoProvider.getWidth(), screenInfoProvider.getHeight());
		setupSprites();
	}

	private synchronized void setupSprites() {
		this.gameSprites = new ArrayList<GameSprite>();
		this.playerSprite = new PlayerSprite(
				new Coordinate(this.screenInfoProvider.getWidth() - 50, this.screenInfoProvider.getHeight() / 2),
				level.getPlayerBeginningRadius(), level.getPlayerBeginningSpeed());
		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < level.getNumGameSprites(); i++) {
					gameSprites.add(spriteCreator.getRandomGameSprite(level, SpriteManager2.this));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		});
		th.start();
	}

	public SpriteManager2(IScreenInfoProvider screenInfoProvider, IPlayerWinHandler winHandler,
			IPlayerLoseHandler lossHandler, IScoreUpdater scoreUpdater) throws MalformedURLException {
		this.level = Level.getLevelOne();
		this.screenInfoProvider = screenInfoProvider;
		this.winHandler = winHandler;
		this.lossHandler = lossHandler;
		this.soundEffectsManager = SoundEffectsManager.getInstance();
		this.scoreUpdater = scoreUpdater;
		this.projectilesToAdd = new ArrayList<ProjectileSprite>();

		// eventually deleted the fully qualified name
		this.spriteCreator = new sprites.SpriteCreator(screenInfoProvider.getWidth(), screenInfoProvider.getHeight());
		setupSprites();
	}

	public ArrayList<GameSprite> getGameSprites() {
		ArrayList<GameSprite> result = (ArrayList<GameSprite>) this.gameSprites.clone();
		return result;
	}

	public Sprite getPlayerSprite() {
		return this.playerSprite;
	}

	public Level getLevel() {
		return this.level;
	}

	public int getLevelAsNumber() {
		return this.level.getLevel();
	}

	public void updatePlayerPosition(KeyEvent evt) {
		switch (evt.getKeyCode()) {
		case KeyEvent.VK_UP:
			this.playerSprite.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			this.playerSprite.moveDown();
			break;
		default:
			return;
		}
	}

	public synchronized void updateSprites() {
		ArrayList<Coordinate> removeList = new ArrayList<Coordinate>();
		int numberOfNewSprites = 0;
		for (GameSprite sprite : this.gameSprites) {
			sprite.updatePosition();
			if (sprite.getX() > this.screenInfoProvider.getWidth()) {
				removeList.add(sprite.getCoordinate());
				numberOfNewSprites = sprite.getClass().getName() == "sprites.ProjectileSprite" ? numberOfNewSprites
						: numberOfNewSprites + 1;
			} else if (CollisionDetection.detectCollisionBetweenSprites(sprite, this.playerSprite)) {
				sprite.handleCollisionWithPlayer(this);
				removeList.add(sprite.getCoordinate());
				numberOfNewSprites++;
			}
		}
		for (Coordinate coordinate : removeList) {
			this.gameSprites.removeIf(c -> c.getX() == coordinate.getX() && c.getY() == coordinate.getY());
		}
		for (int i = 0; i < numberOfNewSprites; i++) {
			this.gameSprites.add(this.spriteCreator.getRandomGameSprite(this.level, this));
		}
		for (ProjectileSprite projectile : this.projectilesToAdd) {
			this.gameSprites.add(projectile);
		}
		this.projectilesToAdd.clear();
	}

	@Override
	public void HandleChangePlayerSprite(int radiusChange, int speedChange) {

		int newRadius = this.playerSprite.getRadius() + radiusChange;
		newRadius = Utility.checkAgainstMinAndMaxValues(newRadius, level.getMaxRadius(), level.getMinRadius());

		int newSpeed = this.playerSprite.getSpeed() + speedChange;
		newSpeed = Utility.checkAgainstMinAndMaxValues(newSpeed, level.getMaxPlayerSpeed(),
				level.getMinPlayerSpeed());

		this.playerSprite = new PlayerSprite(playerSprite.getCoordinate(), newRadius, newSpeed);
	}

	@Override
	public void HandleScoreUpdate(int points) {
		if (points > 0) {
			this.scoreUpdater.addPointsToScore(points);
		} else {
			this.scoreUpdater.removePointsFromScore(Math.abs(points));
		}
	}

	@Override
	public void HandleWin() {
		this.winHandler.handleWin();
	}

	@Override
	public void HandleLose() {
		this.lossHandler.handlePlayerLoss();
	}

	@Override
	public void FireCannon(Coordinate cannonCoordinate) {
		if (cannonCoordinate.getX() < this.playerSprite.getX()) {
			int targetY = Utility.checkAgainstMinAndMaxValues(playerSprite.getY(), screenInfoProvider.getHeight(), 0);
			Coordinate target = new Coordinate(this.playerSprite.getX(), targetY);
			this.projectilesToAdd
					.add(new ProjectileSprite(cannonCoordinate, PROJECTILE_RADIUS, level.getProjectileSpeed(), target, 
							screenInfoProvider.getWidth()));
			soundEffectsManager.PlaySoundEffect("Laser");
		}
	}

	@Override
	public void HandleCollisionSound(String soundName) {
		soundEffectsManager.PlaySoundEffect(soundName);
	}
}
