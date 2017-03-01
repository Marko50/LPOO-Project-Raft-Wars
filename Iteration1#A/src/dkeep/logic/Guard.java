package dkeep.logic;

public abstract class Guard   extends Character{
	/*
	private int[] gPos;
	private char image;
	*/
	protected char[] movs;
	public Guard(int x, int y, char im, char[] m)
	{
		super(x,y,im);
		this.movs = m;
	}
	public char[] getMovs()
	{
		return this.movs;
	}
	
	
	public abstract void gMove(int contador);
	/*
	public void setIm(char im)
	{
		this.image = im;
	}
	public char getImage()
	{
		return this.image;
	}
	public int[] getGpos()
	{
		return this.gPos;
	}
    */
}
