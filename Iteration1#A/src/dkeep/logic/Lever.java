package dkeep.logic;

public class Lever {
	private int[] lPos;
	private char image;
    private boolean pressed;
	public Lever(int x, int y, char im)
	{
		this.lPos[0] = x;
		this.lPos[1] = y;
		this.image = im;
		this.pressed = false;
	}
	
	public boolean getPressed()
	{
		return this.pressed;
	}
}
