package finalPackage;

public class Window {

	int width, height;

	public Window(int x, int y) {
		width = x;
		height = y;
	}

	/**
	 * sets the height of the application
	 * 
	 * @param i
	 */
	public void setHeight(int i) {
		height = i;
	}

	/**
	 * set the width of the application
	 * 
	 * @param i
	 */
	public void setWidth(int i) {
		width = i;
	}

	/**
	 * returns width of application
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * returns height of application
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}
}
