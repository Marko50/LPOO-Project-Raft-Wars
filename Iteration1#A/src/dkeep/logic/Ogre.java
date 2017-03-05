package dkeep.logic;

public class Ogre  extends Character {
	/*
	private int[] oPos;
	private char image;
	*/
	private boolean onTheKey;
	private Club c;
	
	private int stun;
	
	public int getStun()
	{
		return this.stun;
	}
	
	public void setStun(int s)
	{
		this.stun = s;
	}
	
	public Ogre(int x, int y, char im, char cim)
	{
		super(x,y,im);
		Club c2 = new Club(x,y,cim);
		this.c = c2;
		this.onTheKey = false;
		stun = 0;
	}
	
	public void setOnTheKey(boolean b)
	{
		this.onTheKey = b;
	}
	
	public boolean getOnTheKey()
	{
		return this.onTheKey;
	}
	/*
	public void setIm(char im)
	{
		this.image = im;
	}
	public char getImage()
	{
		return this.image;
	}
	public int[] getOpos()
	{
		return this.oPos;
	}
	*/
	public Club getClub()
	{
		return this.c;
	}
}
