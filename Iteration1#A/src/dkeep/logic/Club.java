package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Club  extends Character {
	//private int[] cPos;
	//private char image;
	public Club(int x, int y, char im)
	{
		super(x,y,im);
	}
	/*
	public void setIm(char im)
	{
		this.image = im;
	}
	
	public char getImage()
	{
		return this.image;
	}
	public int[] getCpos()
	{
		return this.cPos;
	}
    */

	@Override
	public void changeBuffImage(char order) {
		if(order == 'w')
    	{
    		try {
				this.setIm(ImageIO.read(new File("images/ClubUp.png")));
			} catch (IOException e) {
			}
    	}
    	
    	else if(order == 'a')
    	{
    		try {
				this.setIm(ImageIO.read(new File("images/ClubLeft.png")));
			} catch (IOException e) {
			}
    		
    	}
    	
    	else if(order == 's')
    	{
    		try {
				this.setIm(ImageIO.read(new File("images/ClubDown.png")));
			} catch (IOException e) {
			}
    		
    	}
    	
    	else if(order == 'd')
    	{
    		try {
    			this.setIm(ImageIO.read(new File("images/ClubRight.png")));
    		} catch (IOException e) {
    		}		
    	}
		
	}
	
}
