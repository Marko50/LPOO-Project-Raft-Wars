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
			try {
				this.setIm(ImageIO.read(new File("images/LeverOn.png")));
			} catch (IOException e) {
				System.out.print("LeverOn sprite not found\n");
				e.printStackTrace();
			}
		
		else
			try {
				this.setIm(ImageIO.read(new File("images/LeverOff.png")));
			} catch (IOException e) {
				System.out.print("LeverOff sprite not found\n");
				e.printStackTrace();
			}
				
	}
}
