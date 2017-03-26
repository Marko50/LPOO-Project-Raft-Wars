/**  
* Character.java - Class for every tile of the game
*/ 

package dkeep.logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Character {
	private int[] pos = new int[2];
	private char image;
	private BufferedImage im;
	
	
	public Character(int x, int y, char im)
	{
		this.pos[0] = x;
		this.pos[1] = y;
		this.image = im;
	}
	
	public void changeBuffIm2(String path, String out) {
		try {
			this.setIm(ImageIO.read(new File(path)));
		}
		catch (IOException e) {
			System.out.print(out);
		}
	}
	
	public abstract void changeBuffImage(char order);
	
	public void setIm(char im)
	{
		this.image = im;
	}
	
	public char getImage()
	{
		return this.image;
	}
	
	public int[] getPos()
	{
		return this.pos;
	}
	
	public void setX(int x)
	{
		this.pos[0] = x;
	}
	
	public void setY(int y)
	{
		this.pos[1] = y;
	}

	public BufferedImage getIm() {
		return im;
	}

	public void setIm(BufferedImage im) {
		this.im = im;
	}

}
