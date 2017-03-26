package dkeep.logic;

import java.util.Random;

/**  
* Drunken.java - Sub-Class of Guard for drunken guard
*/ 
public class Drunken extends Guard{

	/**  
	 * Constructor of Drunken guard extending guard
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param im char representing drunken guard
	 */  
	public Drunken(int x, int y, char im) {
		super(x, y, im);

	}

	
	/**  
	 * Sets x and y coordinates
	 * @param x x coordinate
	 * @param y y coordinate
	 */ 
	public void setXY(int x, int y) {
		this.setX(x);
		this.setY(y);;
	}

	@Override
	public void gMove(int contador) {
		if (this.getSleep() != 0) this.SleepTurn();
		else { Random rn = new Random();
			int num = rn.nextInt(5) + 1; //if 1, guard falls asleep
			if (num != 1) this.setXY(movs[contador][0], movs[contador][1]);
			else {
				this.setXY(movs[contador][0], movs[contador][1]);
				int dir = rn.nextInt(4);
				if (dir == 1)
					if (this.getDirection() == 1) this.setDirection(-1);
					else this.setDirection(1);
				this.fallAsleep();
			}
		}
	}

}
