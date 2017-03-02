package dkeep.logic;

import java.util.Random;

public class Drunken extends Guard{

	public Drunken(int x, int y, char im, char[] m) {
		super(x, y, im, m);

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

				if(movs[contador] == 'w')
				{
					//gPos[1] = gPos[1] - 1;
					this.setY(this.getPos()[1]-1);
				}
				else if(movs[contador] == 'a')
				{
					this.setX(this.getPos()[0]-1);
					//gPos[0] = gPos[0] - 1;
				}
				else if(movs[contador] == 's')
				{
					this.setY(this.getPos()[1]+1);
					//gPos[1] = gPos[1] + 1;
				}
				else if(movs[contador] == 'd')
				{
					this.setX(this.getPos()[0]+1);
					//gPos[0] = gPos[0] + 1;
				}
			}
			else {
				int dir = rn.nextInt(1);
				if (dir == 1)
					this.setDirection(-1);
				this.fallAsleep();
			}
		}
	}
}
