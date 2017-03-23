package dkeep.logic;

public class Rookie extends Guard {

	public Rookie(int x, int y, char im) {
		super(x, y, im);
		
	}

	@Override
	public void gMove(int contador) {
		this.setX(movs[contador][0]); 
		this.setY(movs[contador][1]);		
	}



}
