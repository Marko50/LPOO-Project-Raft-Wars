package dkeep.logic;

public abstract class Guard   extends Character{
	/*
	private int[] gPos;
	private char image;
	*/
	private int direction;
	private int asleep;
	protected char[] movs;
	public Guard(int x, int y, char im, char[] m)
	{
		super(x,y,im);
		this.movs = m;
		direction = 1;
		asleep = 0;
	}
	public char[] getMovs()
	{
		return this.movs;
	}
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int dir) {
		this.direction = dir;
	}
	public int getSleep() {
		return asleep;
	}
	public void SleepTurn() {
		this.asleep--;
	}
	public void fallAsleep() {
		this.asleep = 3;
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
