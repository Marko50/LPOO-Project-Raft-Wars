package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**  
* Club.java - Sub-Class of Character for the clubs
*/ 
public class Club  extends Character {
	/**  
	 * Constructor of Club extending character
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param im char of representing club
	 */  
	public Club(int x, int y, char im) {
		super(x,y,im);
	}
	/**  
	    * Changes buffering image of club depending on where it moved to
	    * @param order char representing where the character's moved
	    */  
	@Override
	public void changeBuffImage(char order) {
		if(order == 'w')
			changeBuffIm2("images/ClubUp.png", "ClubUp sprite not found\n");
    	else if(order == 'a')
    		changeBuffIm2("images/ClubLeft.png", "ClubLeft sprite not found\n");
    	else if(order == 's')
    		changeBuffIm2("images/ClubDown.png", "ClubDown sprite not found\n");
    	else if(order == 'd')
    		changeBuffIm2("images/ClubRight.png", "ClubRight sprite not found\n");
	}
}
