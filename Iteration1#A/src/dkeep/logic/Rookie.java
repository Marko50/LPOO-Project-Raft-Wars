package dkeep.logic;

/**  
* Rookie.java - Sub-Class of guard for rookie guard
*/ 
public class Rookie extends Guard {

	/**
	 * Constructor of rookie guard extending guard
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param im char representing rookie guard
	 */
	public Rookie(int x, int y, char im) {
		super(x, y, im);
	}

	
	@Override
	public void gMove(int contador) {
		this.setX(movs[contador][0]); 
		this.setY(movs[contador][1]);		
	}
}
