package dkeep.logic;

import java.util.Random;

public class Drunken extends Guard{

	public Drunken(int x, int y, char im) {
		super(x, y, im);

	}

	@Override
	public void gMove(int contador) {

		if (this.getSleep() != 0) { //if 0, guard is awake
			this.SleepTurn();
		}
		else {
			Random rn = new Random();
			int num = rn.nextInt(5) + 1;

			if (num != 1) { //If 1, guard falls asleep and doesn't move)
				this.setX(movs[contador][0]); 
				this.setY(movs[contador][1]);
			}
			else {
				this.setX(movs[contador][0]); 
				this.setY(movs[contador][1]);
				int dir = rn.nextInt(4);
				if (dir == 1)
					if (this.getDirection() == 1)
						this.setDirection(-1);
					else this.setDirection(1);
				this.fallAsleep();
			}
		}
	}
}
