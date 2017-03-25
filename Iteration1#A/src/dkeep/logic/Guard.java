package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Guard   extends Character{
	public int[][] movsPossibility = { { 8, 1 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 6, 5 }, { 5, 5 }, { 4, 5 },
			{ 3, 5 }, { 2, 5 }, { 1, 5 }, { 1, 6 }, { 2, 6 }, { 3, 6 }, { 4, 6 }, { 5, 6 }, { 6, 6 }, { 7, 6 },
			{ 8, 6 }, { 8, 5 }, { 8, 4 }, { 8, 3 }, { 8, 2 } };
	
	private int direction;
	private int asleep;
	protected int[][] movs;
	public Guard(int x, int y, char im) {
		super(x,y,im);
		direction = 1;
		asleep = 0;
		changeBuffImage('a');
	}
	
	public void setMovs(int [][] m) {
		this.movs = m;
	}
	
	public int[][] getMovs() {
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
	    if(this.asleep == 0)
	    {
	    	this.setIm('D');
	    }
	}
	public void fallAsleep() {
		this.asleep = 3;
		this.setIm('g');
	}
		
	public abstract void gMove(int contador);
	
	public void changeBuffImage(char order)
	{
		if(order == 'a' || order == 's') {
    		try {
				this.setIm(ImageIO.read(new File("images/GuardMoveLeft.png")));
			} catch (IOException e) {
				System.out.print("GuardMoveLeft sprite not found\n");
			}
    	}
    	else if(order == 'w' || order == 'd') {
    		try {
    			this.setIm(ImageIO.read(new File("images/GuardMoveRight.png")));
    		} catch (IOException e) {
    			System.out.print("GuardMoveRight sprite not found\n");
    		}		
    	}
	}
}