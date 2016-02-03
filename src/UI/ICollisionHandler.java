package UI;

public interface ICollisionHandler {
	public void HandleChangePlayerSprite(int radiusChange, int speedChange);
	public void HandleScoreUpdate(int points);
	public void HandleWin();
	public void HandleLose();
	public void HandleCollisionSound(String soundName);
}
