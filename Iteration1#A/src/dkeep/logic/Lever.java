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
		try {
			this.setIm(ImageIO.read(new File("images/LeverOff.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		else
			try {
				this.setIm(ImageIO.read(new File("images/LeverOff.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	}
}
