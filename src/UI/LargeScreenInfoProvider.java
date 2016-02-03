package UI;

public class LargeScreenInfoProvider implements IScreenInfoProvider, IHeightWidthProvider {

	@Override
	public String getDimensionsName() {
		// TODO Auto-generated method stub
		return "Large";
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 600;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 900;
	}

	@Override
	public int getSidePanelWidth() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public int getFontSize() {
		// TODO Auto-generated method stub
		return 20;
	}



}
