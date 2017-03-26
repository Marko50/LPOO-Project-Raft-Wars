package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**  
* Lever.java - Sub-Class of Character for the lever
*/ 
public class Lever extends Character {
    private boolean pressed;
    /**
     * Constructor of lever extending character
     * @param x x coordinate
     * @param y y coordinate
     * @param im char representing lever
     */
	public Lever(int x, int y, char im)
	{
		super(x,y,im);
		this.pressed = false;	
		changeBuffImage('h');
	}
	
	/**
	 * Whether or not lever has been pressed
	 * @return true if lever has been pressed
	 */
	public boolean getPressed()
	{
		return this.pressed;
	}
	
	/**
	 * Sets whether or not lever has been pressed
	 * @param set whether or not lever has been pressed
	 */
	public void setPressed(boolean set)
	{
		this.pressed = set;
	}

	@Override
	public void changeBuffImage(char order) {
		if(pressed)
			changeBuffIm2("images/LeverOn.png", "LeverOn sprite not found\n");
		else 
			changeBuffIm2("images/LeverOff.png", "LeverOff sprite not found\n");
				
	}
}
