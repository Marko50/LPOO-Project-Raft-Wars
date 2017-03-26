package dkeep.logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

/**  
* Character.java - Class for every tile of the game
*/ 
public abstract class Character {
	private int[] pos = new int[2];
	private char image;
	private BufferedImage im;
	
	 /**  
	    * Constructor of class Character 
	    * @param x Position x of tile
	    * @param y Position y of tile
	    * @param im Letter that represents the tile
	    */  
	public Character(int x, int y, char im)
	{
		this.pos[0] = x;
		this.pos[1] = y;
		this.image = im;
	}
	
	/**  
	    * Changes tile's image to buffering image from path
	    * @param path Path for the image
	    * @param out Display if image is not found
	    */  
	public void changeBuffIm2(String path, String out) {
		try {
			this.setIm(ImageIO.read(new File(path)));
		}
		catch (IOException e) {
			System.out.print(out);
		}
	}
	
	/**  
	    * Changes buffering image of character depending on where it moved to
	    * @param order char representing where the character's moved
	    */  
	public abstract void changeBuffImage(char order);
	
	/**  
	    * Sets tile's char
	    * @param im Char for the tile
	    */  
	public void setIm(char im)
	{
		this.image = im;
	}
	
	/**  
	    * Retrieve the char of tile
	    * @return Char of tile
	    */ 
	public char getImage()
	{
		return this.image;
	}
	
	/**  
	    * Retrieve the position of tile
	    * @return Array with position of tile
	    */ 
	public int[] getPos()
	{
		return this.pos;
	}
	
	/**  
	    * Set X coordinate of tile
	    * @param x X coordinate
	    */ 
	public void setX(int x)
	{
		this.pos[0] = x;
	}
	
	/**  
	    * Set y coordinate of tile
	    * @param y Y coordinate
	    */ 
	public void setY(int y)
	{
		this.pos[1] = y;
	}

	/**  
	    * Retrieve tile's Buffered Image
	    * @return BufferedImage of tile
	    */ 
	public BufferedImage getIm() {
		return im;
	}

	/**  
	    * Set tile's buffered image to param im
	    * @param im image of type BufferedImage
	    */ 
	public void setIm(BufferedImage im) {
		this.im = im;
	}

}
