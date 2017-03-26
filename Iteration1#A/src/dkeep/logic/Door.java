package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**  
* Door.java - Sub-Class of Character for the doors
*/ 
public class Door extends Character{
	private boolean open;
	
	/**  
	 * Constructor of Door extending character
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param im char of representing door
	 * @param op boolean representing whether or not door is open
	 */  
	public Door(int x, int y, char im, boolean op) {
		super(x, y, im);
		open = op;
		changeBuffImage('a');
	}
	
	/**  
	 * Retrieves door open status
	 * @return true if door is open
	 */  
	public boolean isOpen() {
		return open;
	}
	
	/**  
	 * Sets door to param open
	 * @param open boolean representing door status
	 */  
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	/**  
	    * Changes buffering image of door depending on where it moved to
	    * @param order char representing where the character's moved
	    */   
	@Override
	public void changeBuffImage(char order) {
		if(open)
			try {
				this.setIm(ImageIO.read(new File("images/OpenDoor.png")));
			} catch (IOException e) {
				System.out.print("OpenDoor sprite not found\n");
			}
		else
			try {
				this.setIm(ImageIO.read(new File("images/ClosedDoor.png")));
			} catch (IOException e) {
				System.out.print("ClosedDoor sprite not found\n");
			}		
	}
}
