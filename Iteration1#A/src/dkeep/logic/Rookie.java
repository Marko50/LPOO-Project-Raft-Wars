package dkeep.logic;

public class Rookie extends Guard {

	public Rookie(int x, int y, char im) {
		super(x, y, im);
		
	}

	@Override
	public void gMove(int contador) {
		/*
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
		*/
		
		this.setX(movs[contador][0]); 
		this.setY(movs[contador][1]);
		
	}

}
