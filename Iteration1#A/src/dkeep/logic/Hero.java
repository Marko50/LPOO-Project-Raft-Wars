package dkeep.logic;

public class Hero  extends Character {
	
	Club c;
	
	public Hero(int x, int y, char im, char cim)
	{
		super(x,y,im);
		Club c1 = new Club(x,y,cim);
		this.c = c1;
	}
	
	public Club getClub()
	{
		return this.c;
	}

}
