package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class GameInfoSideBar extends JPanel implements IScoreUpdater{
	
	private int score;
	
	JLabel levelTitleLabel;
	JLabel levelLabel;
	
	JLabel scoreTitleLabel;
	JLabel scoreLabel;
	
	public GameInfoSideBar(IScreenDimensionsProvider dimensionsProvider, int level, int score){
		this.setPreferredSize(new Dimension(dimensionsProvider.getSidePanelWidth(), dimensionsProvider.getHeight()));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.DARK_GRAY));
		this.score = score;
		
		Font titleFont = new Font("Helvetica", Font.BOLD, dimensionsProvider.getFontSize());
		Font regularFont = new Font("Helvetica", Font.PLAIN, dimensionsProvider.getFontSize());
		this.levelTitleLabel = new JLabel("Level");
		this.levelTitleLabel.setFont(titleFont);
		this.levelTitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.levelLabel = new JLabel(Integer.toString(level));
		this.levelLabel.setFont(regularFont);
		this.levelLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 5));
		
		this.scoreTitleLabel = new JLabel("Score");
		this.scoreTitleLabel.setFont(titleFont);
		this.scoreTitleLabel.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 5));
		
		this.scoreLabel = new JLabel(Integer.toString(score));
		this.scoreLabel.setFont(regularFont);
		this.scoreLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 5));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(this.levelTitleLabel);
		this.add(this.levelLabel);
		this.add(this.scoreTitleLabel);
		this.add(this.scoreLabel);
	}
	
	private void setScoreLabel(int points){
		this.scoreLabel.setText(Integer.toString(points));
	}
	
	public int getScore(){
		return this.score;
	}
	@Override
	public void addPointsToScore(int points) {
		this.score += points;
		setScoreLabel(this.score);
	}

	@Override
	public void removePointsFromScore(int points) {
		this.score = this.score - points < 0 ? 
				0 : this.score - points;
		setScoreLabel(this.score);
	}
}
