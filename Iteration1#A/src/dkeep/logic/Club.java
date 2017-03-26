/**  
* Club.java - Sub-Class of Character for the clubs
*/ 

package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Club  extends Character {
	public Club(int x, int y, char im) {
		super(x,y,im);
	}

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
