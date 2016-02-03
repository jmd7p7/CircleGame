package UI;

public class SmallScreenInfoProvider implements IScreenInfoProvider, IHeightWidthProvider {

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
	public int getWidth() {
		// TODO Auto-generated method stub
		return 300;
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
