/**  
* Lever.java - Sub-Class of Character for the lever
*/ 

package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Lever extends Character {
    private boolean pressed;
	public Lever(int x, int y, char im)
	{
		super(x,y,im);
		this.pressed = false;	
		changeBuffImage('h');
	}
	
	public boolean getPressed()
	{
		return this.pressed;
	}
	
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
