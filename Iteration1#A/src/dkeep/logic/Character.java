package dkeep.logic;

public class Character {
	private int[] pos;
	private char image;
	
	public Character(int x, int y, char im)
	{
		this.pos[0] = x;
		this.pos[1] = y;
		this.image = im;
	}
	
	public void setIm(char im)
	{
		this.image = im;
	}
	
	public char getImage()
	{
		return this.image;
	}
	
	public int[] getPos()
	{
		return this.pos;
	}
	
	public void setX(int x)
	{
		this.pos[0] = x;
	}
	
	public void setY(int y)
	{
		this.pos[0] = y;
	}

}
