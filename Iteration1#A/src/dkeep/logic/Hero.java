package dkeep.logic;

import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class Hero  extends Character {
	
	public Hero(int x, int y, char im)
	{
		super(x,y,im);
		try {
			this.setIm(ImageIO.read(new File("images/HeroMoveRight.bmp")));
		} catch (IOException e) {
		}

	}
	
    public void changeBuffImage(char order)
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
