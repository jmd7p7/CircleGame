package UI;

public class MediumScreenInfoProvider implements IScreenInfoProvider, IHeightWidthProvider {

	@Override
	public String getDimensionsName() {
		// TODO Auto-generated method stub
		return "Medium";
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 400;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 600;
	}

	@Override
	public int getSidePanelWidth() {
		// TODO Auto-generated method stub
		return 75;
	}

	@Override
	public int getFontSize() {
		// TODO Auto-generated method stub
		return 16;
	}

}
