package UI;

public class ExtraLargeScreenDimensionsProvider implements IScreenDimensionsProvider {

	@Override
	public String getDimensionsName() {
		// TODO Auto-generated method stub
		return "Extra-Large";
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 800;
	}

	@Override
	public int getGameBoardWidth() {
		return 1100;
	}

	@Override
	public int getSidePanelWidth() {
		return 100;
	}

	@Override
	public int getFontSize() {
		// TODO Auto-generated method stub
		return 20;
	}

	

}
