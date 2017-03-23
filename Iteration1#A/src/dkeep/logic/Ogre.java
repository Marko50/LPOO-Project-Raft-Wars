package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ogre  extends Character {
	private boolean onTheKey;
	private Club c;
	
	private int stun;
	
	public int getStun()
	{
		return this.stun;
	}
	
	public void setStun(int s)
	{
		this.stun = s;
	}
	
	public Ogre(int x, int y, char im, char cim)
	{
		super(x,y,im);
		Club c2 = new Club(x,y,cim);
		this.c = c2;
		this.onTheKey = false;
		stun = 0;
	}
	
	public void setOnTheKey(boolean b)
	{
		this.onTheKey = b;
	}
	
	public boolean getOnTheKey()
	{
		return this.onTheKey;
	}

	public Club getClub()
	{
		return this.c;
	}

	@Override
	public void changeBuffImage(char order) {
		if(onTheKey)
		{
			try {
				this.setIm(ImageIO.read(new File("images/key.png")));
			} catch (IOException e) {
			}	
		}
		
		else
		{
			if(order == 'w')
	    	{
	    		try {
					this.setIm(ImageIO.read(new File("images/OgeCima.png")));
				} catch (IOException e) {
				}
	    	}
	    	
	    	else if(order == 'a')
	    	{
	    		try {
					this.setIm(ImageIO.read(new File("images/OgreLeft.png")));
				} catch (IOException e) {
				}
	    		
	    	}
	    	
	    	else if(order == 's')
	    	{
	    		try {
					this.setIm(ImageIO.read(new File("images/OgreRight.png")));
				} catch (IOException e) {
				}
	    		
	    	}
	    	
	    	else if(order == 'd')
	    	{
	    		try {
	    			this.setIm(ImageIO.read(new File("images/OgreLeft.png")));
	    		} catch (IOException e) {
	    		}		
	    	}
		}
		
	}
}
