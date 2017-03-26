package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**  
* Ogre.java - Sub-Class of character for ogres
*/ 
public class Ogre  extends Character {
	private boolean onTheKey;
	private Club c;
	
	private int stun;
	
	/**
	 * Returns ogre's stun status
	 * @return Turns left for the stun
	 */
	public int getStun() {
		return this.stun;
	}
	
	/**
	 * Sets ogre's stun duration
	 * @param s stun duration
	 */
	public void setStun(int s) {
		this.stun = s;
	}
	/**
	 * Constructor of ogre extending character
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param im char representing ogre
	 * @param cim char representing ogre's club
	 */
	public Ogre(int x, int y, char im, char cim)
	{
		super(x,y,im);
		Club c2 = new Club(x,y,cim);
		this.c = c2;
		this.onTheKey = false;
		stun = 0;
		changeBuffImage('w');
	}
	
	/**
	 * Sets whether or not ogre is standing on the key
	 * @param b whether or not ogre is standing on the key
	 */
	public void setOnTheKey(boolean b)
	{
		this.onTheKey = b;
	}
	
	/**
	 * Retrieves whether or not ogre is standing on the key
	 * @return true if ogre is standing on the key
	 */
	public boolean getOnTheKey()
	{
		return this.onTheKey;
	}

	/**
	 * Retrieve ogre's club
	 * @return ogre's club
	 */
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
