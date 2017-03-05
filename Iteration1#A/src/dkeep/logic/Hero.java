package dkeep.logic;

public class Hero  extends Character {
	
	boolean key;
	/*
	private int[] hPos;
	private char image;
	*/
	Club c;
	
	public Hero(int x, int y, char im, char cim)
	{
		super(x,y,im);
		Club c1 = new Club(x,y,cim);
		this.c = c1;
		this.key = false;
	}
	
	public boolean getKey()
	{
		return this.key;
	}
	
	public void setKey(boolean k)
	{
		this.key = k;
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
	public int[] getHpos()
	{
		return this.hPos;
	}
	*/
	public Club getClub()
	{
		return this.c;
	}

}
