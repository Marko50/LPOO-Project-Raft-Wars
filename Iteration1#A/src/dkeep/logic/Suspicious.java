package dkeep.logic;

import java.util.Random;

/**  
* Suspicious.java - Sub-Class of guard for suspicious guard
*/ 
public class Suspicious extends Guard {

	/**
	 * Constructor of suspicious guard extending guard
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param im char representing suspicious guard
	 */
	public Suspicious(int x, int y, char im) {
		super(x, y, im);
	}

	@Override
	public void gMove(int contador) {
		Random rn = new Random();
		
		this.setX(movs[contador][0]); 
		this.setY(movs[contador][1]);
		int dir = rn.nextInt(8);
		if (dir == 1)
			if (this.getDirection() == 1)
				this.setDirection(-1);
			else this.setDirection(1);
	}
}