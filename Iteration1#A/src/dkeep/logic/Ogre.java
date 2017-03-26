/**  
* Ogre.java - Sub-Class of character for ogres
*/ 

package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ogre  extends Character {
	private boolean onTheKey;
	private Club c;
	
	private int stun;
	
	public int getStun() {
		return this.stun;
	}
	
	public void setStun(int s) {
		this.stun = s;
	}
	
	public Ogre(int x, int y, char im, char cim)
	{
		super(x,y,im);
		Club c2 = new Club(x,y,cim);
		this.c = c2;
		this.onTheKey = false;
		stun = 0;
		changeBuffImage('w');
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
			changeBuffIm2("images/key.png", "key sprite not found\n");
		else {
			if(order == 'w')
				changeBuffIm2("images/OgreCima.png", "OgreCima sprite not found\n");
	    	else if(order == 'a')
	    		changeBuffIm2("images/OgreLeft.png", "OgreLeft sprite not found\n");
	    	else if(order == 's')
	    		changeBuffIm2("images/OgreBaixo.png", "OgreBaixo sprite not found\n");
	    	else if(order == 'd')
	    		changeBuffIm2("images/OgreRight.png", "OgreRight sprite not found\n");	
		}
	}
}
