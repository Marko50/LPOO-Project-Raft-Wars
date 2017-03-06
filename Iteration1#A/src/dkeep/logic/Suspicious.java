package dkeep.logic;

import java.util.Random;

public class Suspicious extends Guard {

	public Suspicious(int x, int y, char im, int[][] m) {
		super(x, y, im, m);
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
