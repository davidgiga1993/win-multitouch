package org.devcore.win;

/**
 * Listens for multitouch events
 */
public interface MultitouchProcessor
{

	/**
	 * Gets called on a click / touch event
	 *
	 * @param x       X pos
	 * @param y       Y pos
	 * @param pointer Pointer id
	 * @param mode    Touch mode
	 * @param button  Button id
	 */
	void onTouch(int x, int y, int pointer, int mode, int button);
}
