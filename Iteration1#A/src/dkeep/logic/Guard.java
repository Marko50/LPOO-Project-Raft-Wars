package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Guard.java - Sub-Class of Character for every guard
 */
public abstract class Guard   extends Character{
	public int[][] movsPossibility = { { 8, 1 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 6, 5 }, { 5, 5 }, { 4, 5 },
			{ 3, 5 }, { 2, 5 }, { 1, 5 }, { 1, 6 }, { 2, 6 }, { 3, 6 }, { 4, 6 }, { 5, 6 }, { 6, 6 }, { 7, 6 },
			{ 8, 6 }, { 8, 5 }, { 8, 4 }, { 8, 3 }, { 8, 2 } };
	
	private int direction;
	private int asleep;
	protected int[][] movs;
	
	/**  
	 * Constructor of Guard extending character
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param im char of representing guard
	 */  
	public Guard(int x, int y, char im) {
		super(x,y,im);
		direction = 1;
		asleep = 0;
		changeBuffImage('a');
	}
	
	/**
	 * Set guard moveset
	 * @param m array of integers with the coordinates of every guard move
	 */
	public void setMovs(int [][] m) {
		this.movs = m;
	}
	
	
	/**
	 * Retrieve guard moveset
	 * @return array of integers with the coordinates of every guard move
	 */
	public int[][] getMovs() {
		return this.movs;
	}
	
	/**
	 * Retrieve direction of guard
	 * @return integer representing direction of guard
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Set guard direction
	 * @param dir guard direction
	 */
	public void setDirection(int dir) {
		this.direction = dir;
	}
	
	/**
	 * Retrieve amount of rounds guard has to sleep
	 * @return amount of rounds guard has to sleep
	 */
	public int getSleep() {
		return asleep;
	}
	/**
	 * Spend a guard turn sleeping
	 */
	public void SleepTurn() {
		this.asleep--;
	    if(this.asleep == 0)
	    {
	    	this.setIm('D');
	    }
	}
	
	/**
	 * Sets guard to sleep 3 rounds
	 */
	public void fallAsleep() {
		this.asleep = 3;
		this.setIm('g');
	}
	
	/**
	 * /**  
	 * Guard movement
	 * @param contador which move the guard needs to make
	 */ 
	public abstract void gMove(int contador);
	

	public void changeBuffImage(char order)
	{
		if(order == 'a' || order == 's') 
			changeBuffIm2("images/GuardMoveLeft.png", "GuardMoveLeft sprite not found\n");
    	else if(order == 'w' || order == 'd')
    		changeBuffIm2("images/GuardMoveRight.png", "GuardMoveRight sprite not found\n");	
	}
}