package dkeep.logic;

import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class Hero  extends Character {
	private boolean hasKey;
	public Hero(int x, int y, char im)
	{
		super(x,y,im);
		try {
			this.setIm(ImageIO.read(new File("images/HeroMoveRight.png")));
		} catch (IOException e) {
		}
		setHasKey(false);

	}
	
    public void changeBuffImage(char order)
    {
		if (hasKey)

			try {
				this.setIm(ImageIO.read(new File("images/key.png")));
			} catch (IOException e) {
			}	
        	
		else
		{
			if(order == 'w')
	    	{
	    		try {
					this.setIm(ImageIO.read(new File("images/HeroMoveUp.png")));
				} catch (IOException e) {
				}
	    	}
	    	
	    	else if(order == 'a')
	    	{
	    		try {
					this.setIm(ImageIO.read(new File("images/HeroMoveLeft.png")));
				} catch (IOException e) {
				}
	    		
	    	}
	    	
	    	else if(order == 's')
	    	{
	    		try {
					this.setIm(ImageIO.read(new File("images/HeroMoveDown.png")));
				} catch (IOException e) {
				}
	    		
	    	}
	    	
	    	else if(order == 'd')
	    	{
	    		try {
	    			this.setIm(ImageIO.read(new File("images/HeroMoveRight.png")));
	    		} catch (IOException e) {
	    		}		
	    	}
		}
    }

	public boolean isHasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}
}
