package UI;

public class SmallScreenDimensionsProvider implements IScreenDimensionsProvider {

	@Override
	public String getDimensionsName() {
		return "Small";
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 200;
	}

	@Override
	public int getGameBoardWidth() {
		// TODO Auto-generated method stub
		return 250;
	}

	@Override
	public int getSidePanelWidth() {
		// TODO Auto-generated method stub
		return 50;
	}

	@Override
	public int getFontSize() {
		// TODO Auto-generated method stub
		return 13;
	}
	
	
}
