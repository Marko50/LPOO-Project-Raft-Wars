package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**  
* Key.java - Sub-Class of Character for the key
*/ 
public class Key extends Character  implements Cloneable{

    private boolean Used;
    private boolean pickedUp;
    /**
     * Key constructor extending character
     * @param x
     * @param y
     * @param im
     */
	public Key(int x, int y, char im) {
		super(x, y, im);
		this.Used = false;
		this.pickedUp = false;
		changeBuffImage('w');
	}

	
	/**
	 * Whether or not key has been used
	 * @return true if key has been used
	 */
	public boolean getUsed() {
		return Used;
	}
	/**
	 * Sets whether or not key has been used
	 * @param Used whether or not key has been used
	 */
	public void setUsed(boolean Used) {
		this.Used = Used;
	}
	
	/**
	 * Whether or not key has been picked up
	 * @return true if key has been picked up
	 */
	public boolean isPickedUp() {
		return pickedUp;
	}
	
	/**
	 * Sets whether or not key has been picked up
	 * @param pickedUp whether or not key has been picked up
	 */
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
	
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	@Override
	public void changeBuffImage(char order) {
		if (pickedUp)
			this.setIm(null);
		else
			try {
				this.setIm(ImageIO.read(new File("images/key.png")));
			} catch (IOException e) {
				System.out.print("key sprite not found\n");
			}
	}

}
