package dkeep.logic;

public class Ogre {
	private int[] oPos;
	private char image;
	Club c;
	
	public Ogre(int x, int y, char im, char cim)
	{
		this.oPos[0] = x;
		this.oPos[1] = y;
		this.image = im;
		Club c2 = new Club(x,y,cim);
		this.c = c2;
	}

}
