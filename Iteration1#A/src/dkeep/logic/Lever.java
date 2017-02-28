package dkeep.logic;

public class Lever extends Character {
	/*
	private int[] lPos;
	private char image;
	*/
    private boolean pressed;
    private boolean DoorOpen;
	public Lever(int x, int y, char im)
	{
		super(x,y,im);
		this.pressed = false;
		this.DoorOpen = false;
		
	}
	/*
	public void setIm(char im)
	{
		this.image = im;
	}
	*/
	
	public boolean getDoorOpen()
	{
		return this.DoorOpen;
	}
	
	public boolean getPressed()
	{
		return this.pressed;
	}
	
	public void setDoorOpen(boolean set)
	{
		this.DoorOpen = set;
	}
	
	public void setPressed(boolean set)
	{
		this.pressed = set;
	}
	/*
	public char getImage()
	{
		return this.image;
	}
	public int[] getLpos()
	{
		return this.lPos;
	}
	*/
}
