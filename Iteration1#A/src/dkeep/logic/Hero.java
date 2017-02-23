package dkeep.logic;

public class Hero {
	private int[] hPos;
	private char image;
	Club c;
	
	public Hero(int x, int y, char im, char cim)
	{
		this.hPos[0] = x;
		this.hPos[1] = y;
		this.image = im;
		Club c1 = new Club(x,y,cim);
		this.c = c1;
	}

}
