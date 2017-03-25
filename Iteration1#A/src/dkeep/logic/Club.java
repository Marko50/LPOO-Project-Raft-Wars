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
		if(order == 'w') {
    		try {
				this.setIm(ImageIO.read(new File("images/ClubUp.png")));
			} catch (IOException e) {
			}
    	}
    	else if(order == 'a') {
    		try {
				this.setIm(ImageIO.read(new File("images/ClubLeft.png")));
			} catch (IOException e) {
			}
    	} 
    	else if(order == 's') {
    		try {
				this.setIm(ImageIO.read(new File("images/ClubDown.png")));
			} catch (IOException e) {
			}
    	}
    	else if(order == 'd') {
    		try {
    			this.setIm(ImageIO.read(new File("images/ClubRight.png")));
    		} catch (IOException e) {
    		}		
    	}
	}
}
