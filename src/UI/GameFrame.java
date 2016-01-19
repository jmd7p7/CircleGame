package UI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import GamePlay.PlayerLoseHandler;
import GamePlay.PlayerWinHandler;
import GamePlay.SpriteManager;
import Sprite.Sprite;
import Level.Level;

public class GameFrame extends JFrame implements PlayerWinHandler, PlayerLoseHandler{
	IScreenDimensionsProvider dimensionsProvider;	
	private GameBoard gameBoard;
	private GameInfoSideBar sideBar;
	private static final int LEVEL_ONE = 1;
	private static final int ZERO_SCORE = 0;
	
	public GameFrame() {
		
		this.dimensionsProvider = new LargeScreenDimensionsProvider();
		sideBar = new GameInfoSideBar(dimensionsProvider, LEVEL_ONE, ZERO_SCORE);
		gameBoard = new GameBoard(this, this, dimensionsProvider, sideBar);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(gameBoard, BorderLayout.CENTER);
		cp.add(sideBar, BorderLayout.EAST);

		// JFrame responds to up and down arrow KeyEvent
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				gameBoard.updatePlayerPosition(evt);
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the close button
		setTitle("Sprite Game");
		requestFocus(); // set the focus to JFrame to receive KeyEvent
	}

	/** The entry main() method */
	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		game.pack();
		game.setVisible(true);
		game.start();
	}

	private void start() {
		gameBoard.gameLoop();
	}

	@Override
	public void handleWin() {
		int result = JOptionPane.showConfirmDialog(null, "Congrats, you've won! Continue to the next level?", "Win", 
				JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION){
			sideBar = new GameInfoSideBar(dimensionsProvider, gameBoard.getLevelAsNumber() + 1, sideBar.getScore());
			gameBoard = new GameBoard(gameBoard.getLevel(), this, this, dimensionsProvider, sideBar);			
			startNextGame();
		}
		else{
			System.exit(0);
		}
		
	}

	@Override
	public void handlePlayerLoss() {
		int result = JOptionPane.showConfirmDialog(null, "Sorry, you've lost! Play again?", "Lost", 
				JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION){
			sideBar = new GameInfoSideBar(dimensionsProvider, LEVEL_ONE, ZERO_SCORE);	
			gameBoard = new GameBoard(this, this, dimensionsProvider, sideBar);	
			startNextGame();
		}
		else{
			System.exit(0);
		}
	}
	
	private void startNextGame() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(gameBoard, BorderLayout.CENTER);
		cp.add(sideBar, BorderLayout.EAST);
		this.validate();
		gameBoard.gameLoop();
	}
}