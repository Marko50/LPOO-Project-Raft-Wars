package dkeep.logic;

import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

/**  
* Hero.java - Sub-Class of Character for the Hero
*/ 
public class Hero  extends Character {
	private boolean hasKey;
	/**
	 * Constructor of Hero extending character
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param im char representing Hero
	 */
	public Hero(int x, int y, char im) {
		super(x,y,im);
		setHasKey(false);
		changeBuffImage('d');

	}
	
    public void changeBuffImage(char order) {
		if (hasKey)
			changeBuffIm2("images/key.png", "key sprite not found\n");	
		else {
			if(order == 'w') 
				changeBuffIm2("images/HeroMoveUp.png", "HeroMoveUp sprite not found\n");
	    	else if(order == 'a')
	    		changeBuffIm2("images/HeroMoveLeft.png", "HeroMoveLeft sprite not found\n");
	    	else if(order == 's')
	    		changeBuffIm2("images/HeroMoveDown.png", "HeroMoveDown sprite not found\n");
	    	else if(order == 'd')
	    		changeBuffIm2("images/HeroMoveRight.png", "HeroMoveRight sprite not found\n");	
		}
    }

    /**
     * Return whether or not hero has the key
     * @return true if hero has the key
     */
	public boolean isHasKey() {
		return hasKey;
	}

	/**
	 * Sets whether or not hero has the key
	 * @param hasKey whether or not hero has the key
	 */
	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}
}
